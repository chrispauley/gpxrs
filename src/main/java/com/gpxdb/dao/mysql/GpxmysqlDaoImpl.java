package com.gpxdb.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.gpxdb.dao.GpxDao;
import com.gpxdb.dao.MetadataDao;
import com.gpxdb.dao.RteDao;
import com.gpxdb.dao.TrkDao;
import com.gpxdb.dao.WptDao;

import org.gpxdb.model.*;

// TODO: Auto-generated Javadoc
/**
 * The Class GpxmysqlDaoImpl.
 */
public class GpxmysqlDaoImpl implements GpxDao {

	/** The Constant GPX_LIST_LIMIT. */
	private static final long GPX_LIST_LIMIT = 1000;

	/** The logger. */
//	final Logger logger = LoggerFactory.getLogger(GpxmysqlDaoImpl.class);

	/** The _con. */
	private Connection _con;

	/**
	 * Instantiates a new gpxmysql dao impl.
	 */
	public GpxmysqlDaoImpl() {
		super();
	}

	/**
	 * Instantiates a new gpxmysql dao impl.
	 * 
	 * @param connection
	 *            the connection
	 */
	public GpxmysqlDaoImpl(Connection connection) {
		super();
		this._con = connection;
	}

	/**
	 * Gets the connection.
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		return _con;
	}

	/**
	 * Sets the connection.
	 * 
	 * @param connection
	 *            the new connection
	 */
	public void setConnection(Connection connection) {
		this._con = connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.GpxDao#delete(long)
	 */
	public void delete(long gpxid) {
		long metid = 0;
		PreparedStatement ps = null;
		ResultSet rs;
		try {

			ps = _con
					.prepareStatement("SELECT * FROM gpx WHERE gpxid=" + gpxid);
			rs = ps.executeQuery();
			while (rs.next()) {
				metid = rs.getLong("metid");
			}
			ps.close();

			if (metid > 0) {
				MetadataMysqlDaoImpl mDao = new MetadataMysqlDaoImpl(_con);
				mDao.deleteMetadata(metid);
			}
			WptDao wDao = new WptMysqlDaoImpl(_con);
			wDao.deleteWpts(gpxid);

			RteDao rDao = new RtemysqlDaoImpl(_con);
			rDao.deleteRtes(gpxid);

			TrkDao tDao = new TrkmysqlDaoImpl(_con);
			tDao.deleteTrks(gpxid);

			ps = _con.prepareStatement("DELETE FROM gpx WHERE gpxid=" + gpxid);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.GpxDao#update(com.gpx.model.castor.Gpx)
	 */
	public long update(Gpx gpx) {
		long gpxid = gpx.getValueObjectId();
		if (gpxid == 0) {
			return 0;
		}
		if (gpx.getMetadata() != null) {
			MetadataMysqlDaoImpl mDao = new MetadataMysqlDaoImpl(_con);
			mDao.updateMetadata(gpx.getMetadata());
		}
		// TODO Test for wpt, rte, trk updates
		return gpx.getValueObjectId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.GpxDao#update(com.gpx.model.castor.Gpx)
	 */
	public long insert(Gpx gpx) {
		PreparedStatement ps = null;

		Wpt wpt;
		long gpxid = 0;

		try {
			ps = _con
					.prepareStatement("INSERT INTO gpx (version, creator) VALUES (?, ?)");
			ps.setString(1, gpx.getVersion());
			ps.setString(2, gpx.getCreator());
			ps.execute();
			ps.close();

			gpxid = this.getLastUpdateId(_con);
			gpx.setValueObjectId(gpxid);
			if (gpx.getMetadata() != null) {
				MetadataMysqlDaoImpl mDao = new MetadataMysqlDaoImpl(_con);
				mDao.insertMetadata(gpx);
			}

			if (gpx.getWpt() != null) {
				WptMysqlDaoImpl wDao = new WptMysqlDaoImpl(_con);
				for (int i = 0; i < gpx.getWpt().size(); i++) {
					wpt = gpx.getWpt().get(i);
					wDao.insertWaypoint(gpxid, wpt);
				}
			}

//			if (gpx.getRte() != null) {
//				RtemysqlDaoImpl rDao = new RtemysqlDaoImpl(_con);
//				rDao.addRtes(gpx);
//			}
//
//			if (gpx.getTrk() != null) {
//				TrkDao trkdao = new TrkmysqlDaoImpl(_con);
//				trkdao.addTrks(gpx);
//			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return gpxid;
	}

	/*
	 * Retrieves gpx by its metadata name (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.GpxDao#load(com.gpx.model.castor.Gpx)
	 */
	public ArrayList<Gpx> listByGpxName(String name) {
		ArrayList<Gpx> result = new ArrayList<Gpx>();
		Gpx gpx = null;
		long resultCount = 0;
		long startindex = 0;
		long limit = GPX_LIST_LIMIT;

		if (name == null) {
			return null;
		}

		try {
			String sql = "SELECT count(g.gpxid) as resultcount "
					+ "FROM metadata m, gpx g WHERE g.gpxid=m.gpxid "
					+ "AND m.name like '%" + name + "%'";

			PreparedStatement ps = _con.prepareStatement(sql);
			ResultSet rscount = ps.executeQuery();
			while (rscount.next()) {
				resultCount = rscount.getLong("resultcount");
			}
			rscount.close();
			ps.close();
			if (resultCount == 0) {
				return result;
			}
			
			
			String sql2 = "SELECT g.gpxid as gpxid, g.creator, g.version, "
					+ "g.extid, m.metid FROM metadata m, gpx g "
					+"WHERE g.gpxid=m.gpxid AND m.name like '%" + name + "%'";
			// Adjust this for performance needs.
			if(resultCount>GPX_LIST_LIMIT){
				sql2 += " LIMIT " + startindex + ", " + limit;
			}

			long metid = 0;
			long extid = 0;
			PreparedStatement ps2 = _con.prepareStatement(sql2);
			ResultSet rs = ps2.executeQuery();
			while (rs.next()) {
				gpx = new Gpx();
				gpx.setValueObjectId(rs.getLong("gpxid"));
				gpx.setCreator(rs.getString("creator"));
				gpx.setVersion(rs.getString("version"));
				metid = rs.getLong("metid");
				extid = rs.getLong("extid");
				if(extid>0){
					gpx.setExtensions(loadGpxExtensions(extid));
				}
				result.add(gpx);
			}
			rs.close();
			ps2.close();
			
			MetadataMysqlDaoImpl metadatadao = new MetadataMysqlDaoImpl(_con);
			for (int i = 0; i < result.size(); i++) {
				gpx = (Gpx) result.get(i);
				metadatadao.getMetadata(gpx);
			}


		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * Load gpx extensions.
	 *
	 * @param extid the extid
	 * @return the extensions
	 */
	private Extensions loadGpxExtensions(long extid) {
		Extensions result = new Extensions();
		// Add Extensions here as needed.
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.GpxDao#findById(long)
	 */
	public Gpx findById(long gpxid) {
		Gpx gpx = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			statement = _con.createStatement();
			rs = statement.executeQuery("SELECT * FROM gpx WHERE gpxid="
					+ gpxid);
			while (rs.next()) {
				gpx = new Gpx();
				gpx.setValueObjectId(gpxid);
				gpx.setVersion(rs.getString("version"));
				gpx.setCreator(rs.getString("creator"));
				if(rs.getLong("extid")>0){
					// Not implemented. Add a dao and call it here.
					gpx.setExtensions(loadGpxExtensions(rs.getLong("extid")));
				}				
			}
			rs.close();
			statement.close();
			if (gpx == null) {
				return null;
			}

			MetadataDao metadatadao = new MetadataMysqlDaoImpl(_con);
			metadatadao.getMetadata(gpx);

			WptDao wptdao = new WptMysqlDaoImpl(_con);
			wptdao.getWpts(gpx);

			RteDao rteDao = new RtemysqlDaoImpl(_con);
			rteDao.getRtes(gpx);

			TrkDao trkdao = new TrkmysqlDaoImpl(_con);
			trkdao.getTrksForGpx(gpx);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return gpx;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.GpxDao#listAll()
	 */
	public ArrayList<Gpx> listAll() {
		ArrayList<Gpx> result = new ArrayList<Gpx>();
		Gpx gpx = null;
		long gpxid = 0;
		Statement statement = null;
		ResultSet rs = null;

		try {
			statement = _con.createStatement();
			rs = statement.executeQuery("Select * from gpx order by gpxid");
			while (rs.next()) {
				gpx = new Gpx();
				gpx.setCreator(rs.getString("creator"));
				gpx.setVersion(rs.getString("version"));
				gpxid = rs.getLong("gpxid");
				gpx.setValueObjectId(gpxid);
				// metid = rs.getLong("metid");
				// extid = rs.getLong("extid");
				result.add(gpx);
			}
			rs.close();
			statement.close();

			MetadataMysqlDaoImpl metadatadao = new MetadataMysqlDaoImpl(_con);
			for (int i = 0; i < result.size(); i++) {
				gpx = (Gpx) result.get(i);
				metadatadao.getMetadata(gpx);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * Gets the last update id.
	 * 
	 * @param con
	 *            the con
	 * @return the last update id
	 * @throws SQLException
	 *             the sQL exception
	 */
	private long getLastUpdateId(Connection con) throws SQLException {
		long result = 0;
		ResultSet resultset = null;
		Statement statement = con.createStatement();
		resultset = statement.executeQuery("SELECT LAST_INSERT_ID()");
		while (resultset.next()) {
			result = resultset.getInt(1);
		}
		resultset.close();
		statement.close();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.GpxDao#listAllPaged(long, long)
	 */
	public ArrayList<Gpx> listAllPaged(long startindex, long limit, String orderby) {
		ArrayList<Gpx> result = new ArrayList<Gpx>();
		Gpx gpx = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			statement = _con.createStatement();
			String s ="Select * from gpx order by gpxid "
				+ "LIMIT " + startindex + ", " + limit;
			if(s!=null && s.length()>0){
				// TODO add orderby clause
			}
			rs = statement.executeQuery(s);
			while (rs.next()) {
				gpx = new Gpx();
				gpx.setCreator(rs.getString("creator"));
				gpx.setVersion(rs.getString("version"));
				gpx.setValueObjectId(rs.getLong("gpxid"));
				if(rs.getLong("extid")>0){
					gpx.setExtensions(loadGpxExtensions(rs.getLong("extid")));
				}
				result.add(gpx);
			}
			rs.close();
			statement.close();

			MetadataMysqlDaoImpl metadatadao = new MetadataMysqlDaoImpl(_con);
			for (int i = 0; i < result.size(); i++) {
				gpx = (Gpx) result.get(i);
				metadatadao.getMetadata(gpx);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.GpxDAO#getTotalCount()
	 */
	public long getTotalCount() {
		long totalcount = 0;
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT count(gpxid) AS totalcount FROM gpx");
			while (rs.next()) {
				totalcount = rs.getLong("totalcount");
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return totalcount;
	}


	/* (non-Javadoc)
	 * @see org.gpxdb.dao.GpxDao#listAllByCreator(java.lang.String, long, long)
	 */
	public ArrayList<Gpx> listAllByCreator(String creator, long startindex,
			long limit) {
		ArrayList<Gpx> result = new ArrayList<Gpx>();
		Gpx gpx = null;
		long resultCount = 0;

		if (creator == null) {
			return null;
		}
		if (limit==0){
			limit = GPX_LIST_LIMIT;
		}

		try {
			String sql = "SELECT count(g.gpxid) resultcount as gpxcount "
					+ "FROM metadata m, gpx g WHERE g.gpxid=m.gpxid "
					+ "AND g.creator like '%" + creator + "%'";

			PreparedStatement ps = _con.prepareStatement(sql);
			ResultSet rscount = ps.executeQuery();
			while (rscount.next()) {
				resultCount = rscount.getLong("resultcount");
			}
			rscount.close();
			ps.close();
			if (resultCount == 0) {
				return result;
			}
			
			
			String sql2 = "SELECT g.gpxid, g.creator, g.version, g.extid, m.metid"
					+ "FROM metadata m, gpx g WHERE g.gpxid=m.gpxid "
					+ "AND g.creator like '%" + creator + "%'";
			// Adjust this for performance needs.
			if(resultCount>GPX_LIST_LIMIT){
				sql2 += " LIMIT " + startindex + ", " + limit;
			}

			long metid = 0;
			long extid = 0;
			PreparedStatement ps2 = _con.prepareStatement(sql);
			ResultSet rs = ps2.executeQuery();
			while (rs.next()) {
				gpx = new Gpx();
				gpx.setValueObjectId(rs.getLong("gpxid"));
				gpx.setCreator(rs.getString("creator"));
				gpx.setVersion(rs.getString("version"));
				metid = rs.getLong("metid");
				extid = rs.getLong("extid");
				if(extid>0){
					gpx.setExtensions(loadGpxExtensions(extid));
				}
				result.add(gpx);
			}
			rs.close();
			ps2.close();
			
			MetadataMysqlDaoImpl metadatadao = new MetadataMysqlDaoImpl(_con);
			for (int i = 0; i < result.size(); i++) {
				gpx = (Gpx) result.get(i);
				metadatadao.getMetadata(gpx);
			}


		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
