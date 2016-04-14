package com.gpxdb.dao.mysql;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.gpxdb.dao.TrkptDao;

import org.gpxdb.model.*;


// TODO: Auto-generated Javadoc
/**
 * The Class TrkptMysqlDaoImpl.
 */
public class TrkptMysqlDaoImpl implements TrkptDao {

	/** The _con. */
	private Connection _con;


	/** The trkpt dao. */
	private static TrkptMysqlDaoImpl trkptDao = null;


	/**
	 * Gets the trkpt dao.
	 *
	 * @return the trkpt dao
	 */
	public static TrkptDao getTrkptDao() {
		if (trkptDao == null) {
			trkptDao = new TrkptMysqlDaoImpl();
		}
		return trkptDao;
	}

	/**
	 * Instantiates a new wpt mysql dao impl.
	 */
	public TrkptMysqlDaoImpl() {
		super();
	}

	/**
	 * Instantiates a new trkpt mysql dao impl.
	 *
	 * @param con the con
	 */
	public TrkptMysqlDaoImpl(Connection con) {
		if (_con == null) {
			_con = con;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.TrkptDao#deleteTrkpt(long)
	 */
	public void deleteTrkpt(long tptid) {
		try {
			CallableStatement cs;
			cs = _con.prepareCall("{call deleteTrkpt(?)}");
			cs.setLong(1, tptid);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.TrkptDao#deleteTrkptExtensions(long)
	 */
	public void deleteTrkptExtensions(long tptid) {
		try {
			CallableStatement cs;
			cs = _con.prepareCall("{call deleteTrkptExtensions(?)}");
			cs.setLong(1, tptid);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.TrkptDao#deleteTrkptLinks(long)
	 */
	public void deleteTrkptLinks(long tptid) {
		try {
			CallableStatement cs;
			cs = _con.prepareCall("{call deleteTrkptLinks(?)}");
			cs.setLong(1, tptid);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.TrkptDao#findTrkptById(long)
	 */
	public Trkpt findTrkptById(long tptid) {
		Trkpt trkpt = null;
		String sql = "SELECT * FROM trkpt WHERE tptid=" + tptid;
		Statement statement;
		ResultSet rs;
		try {
			statement = _con.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				trkpt = getTrkptFromResultSet(rs);
			}
			rs.close();
			statement.close();
			if (trkpt != null) {
				this.getTrkptLinks(trkpt);
				// this.getExtensions(trkpt);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return trkpt;
	}

	/**
	 * Gets the last update id.
	 *
	 * @return the last update id
	 * @throws SQLException the sQL exception
	 */
	private long getLastUpdateId() throws SQLException {
		long result = 0;
		ResultSet resultset = null;
		Statement statement = _con.createStatement();
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
	 * @see org.gpxdb.dao.TrkptDao#getTotalCount()
	 */
	public long getTotalCount() {
		long tptcount = 0;
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT count(tptid) AS tptcount FROM trkpt");
			while (rs.next()) {
				tptcount = rs.getLong("tptcount");
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return tptcount;
	}

	/* (non-Javadoc)
	 * @see com.gpxdb.dao.TrkptDao#getTrkptsByTrksegId(long)
	 */
	public ArrayList<Trkpt> getTrkptsByTrksegId(long tsid) {
		Trkpt trkpt;
		ArrayList<Trkpt> trkptList = new ArrayList<Trkpt>();
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM trkpt "
					+ "WHERE tsid=" + tsid);
			while (rs.next()) {
				trkpt = this.getTrkptFromResultSet(rs);
				// @TODO extensions
				this.getTrkptLinks(trkpt);
				trkptList.add(trkpt);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return trkptList;
	}
	
	/**
	 * Gets the trkpt from result set.
	 *
	 * @param rs the rs
	 * @return the trkpt from result set
	 * @throws SQLException the sQL exception
	 */
	private Trkpt getTrkptFromResultSet(ResultSet rs) throws SQLException {
		Trkpt tp = new Trkpt();
		tp.setValueObjectId(rs.getLong("tptid"));
		tp.setLat(rs.getBigDecimal("lat"));
		tp.setLon(rs.getBigDecimal("lon"));
		tp.setEle(rs.getBigDecimal("ele"));
		if (rs.getTime("time") != null) {
			tp.setTime(rs.getTimestamp("time"));
		}
		tp.setMagvar(rs.getBigDecimal("magvar"));
		tp.setGeoidheight(rs.getBigDecimal("geoidheight"));
		tp.setName(rs.getString("name"));
		tp.setCmt(rs.getString("cmt"));
		tp.setType(rs.getString("type"));
		tp.setDesc(rs.getString("desc"));
		tp.setSrc(rs.getString("src"));
		tp.setSym(rs.getString("sym"));
		if (rs.getString("fix") != null) {
			tp.setFix(rs.getString("fix"));
		}
		tp.setSat(BigInteger.valueOf(rs.getLong("sat")));
		tp.setHdop(rs.getBigDecimal("hdop"));
		tp.setVdop(rs.getBigDecimal("vdop"));
		tp.setPdop(rs.getBigDecimal("pdop"));
		tp.setAgeofdgpsdata(rs.getBigDecimal("ageofdgpsdata"));
		tp.setDgpsid(rs.getInt("dgpsid"));
		return tp;
	}

	/**
	 * Gets the trkpt links.
	 *
	 * @param trkpt the trkpt
	 * @return the trkpt links
	 * @throws SQLException the sQL exception
	 */
	private void getTrkptLinks(Trkpt trkpt) throws SQLException {
		Link link = null;
		Statement statement = _con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM link "
				+ "WHERE tptid=" + trkpt.getValueObjectId());
		while (rs.next()) {
			link = new Link();
			link.setValueObjectId(rs.getLong("linkid"));
			link.setHref(rs.getString("href"));
			link.setText(rs.getString("text"));
			link.setType(rs.getString("type"));
			trkpt.getLink().add(link);
		}
		rs.close();
		statement.close();
		return;
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.TrkptDao#insert(long, org.gpxdb.model.Trkpt)
	 */
	public long insert(long tsid, Trkpt trkpt) {
		long tptid = 0;
		try {
			String sql = "INSERT INTO trkpt "
					+ "(tsid,  lat, lon, ele, time,  magvar, geoidheight, "
					+ " name, cmt, type, `desc`, src, sym, fix, sat, hdop, vdop, pdop, ageofdgpsdata, dgpsid) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = _con.prepareStatement(sql);
			ps.setLong(1, tsid);
			ps.setBigDecimal(2, trkpt.getLat());
			ps.setBigDecimal(3, trkpt.getLon());
			ps.setBigDecimal(4, trkpt.getEle());
			if (trkpt.getTime() != null) {
				java.sql.Timestamp trkptTimestamp = new java.sql.Timestamp(
						trkpt.getTime().getTime());
				ps.setTimestamp(5, trkptTimestamp);
			} else {
				ps.setTimestamp(5, null);
			}
			ps.setBigDecimal(6, trkpt.getMagvar());
			ps.setBigDecimal(7, trkpt.getGeoidheight());
			ps.setString(8, trkpt.getName());
			ps.setString(9, trkpt.getCmt());
			ps.setString(10, trkpt.getType());
			ps.setString(11, trkpt.getDesc());
			ps.setString(12, trkpt.getSrc());
			// link
			ps.setString(13, trkpt.getSym());
			if (trkpt.getFix() != null) {
				ps.setString(14, trkpt.getFix());
			} else {
				ps.setString(14, null);
			}
			ps.setLong(15, trkpt.getSat().longValue());
			ps.setBigDecimal(16, trkpt.getHdop());
			ps.setBigDecimal(17, trkpt.getVdop());
			ps.setBigDecimal(18, trkpt.getPdop());
			ps.setBigDecimal(19, trkpt.getAgeofdgpsdata());
			ps.setLong(20, trkpt.getDgpsid());
			ps.executeUpdate();
			ps.close();
			tptid = this.getLastUpdateId();
			trkpt.setValueObjectId(tptid);

			if (trkpt.getLink() != null) {
				insertTrkptLinks(trkpt);
			}
			if (trkpt.getExtensions() != null) {
//				insertTrkptExtensions(trkpt);
			}
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return tptid;
	}

	/**
	 * Insert trkpt links.
	 *
	 * @param trkpt the trkpt
	 * @throws SQLException the exception
	 */
	private void insertTrkptLinks(Trkpt trkpt) throws SQLException {
		long tptid = trkpt.getValueObjectId();
		PreparedStatement ps2 = _con.prepareStatement("INSERT INTO link "
				+ "(tptid, href, text, type) VALUES (?, ?, ?, ?)");
		Link link = null;
		for (int i = 0; i < trkpt.getLink().size(); i++) {
			link = trkpt.getLink().get(i);
			ps2.setLong(1, tptid);
			ps2.setString(2, link.getHref());
			ps2.setString(3, link.getText());
			ps2.setString(4, link.getType() + "");
			ps2.executeUpdate();
		}
		ps2.close();
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.TrkptDao#listAllTrkptPaged(long, long)
	 */
	public ArrayList<Trkpt> listAllTrkptPaged(long startindex, long limit, String orderby) {
		Trkpt trkpt;
		ArrayList<Trkpt> trkptList = new ArrayList<Trkpt>();
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM trkpt "
					+ "LIMIT " + startindex + ", " + limit);
			while (rs.next()) {
				trkpt = this.getTrkptFromResultSet(rs);
				// @TODO extensions
				this.getTrkptLinks(trkpt);
				trkptList.add(trkpt);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return trkptList;
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

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.TrkptDao#updateTrkpt(org.gpxdb.model.Trkpt)
	 */
	public void updateTrkpt(Trkpt trkpt) {
		try {
			long tsid = 0;
			if (trkpt.getValueObjectId() == 0) {
				return;
			}
			Statement statement = _con.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT tsid FROM trkpt WHERE tptid="
							+ trkpt.getValueObjectId());
			while (rs.next()) {
				tsid = rs.getLong("tsid");
			}
			if (tsid == 0) {
				return;
			}
			rs.close();
			statement.close();

			String sql = "UPDATE trkpt "
					+ "SET tsid=?, lat=?, lon=?, ele=?, time=?, magvar=?, geoidheight=?, name=?,"
					+ "cmt=?, type=?, `desc`=?, src=?, sym=?, fix=?, sat=?, hdop=?, vdop=?, pdop=?,"
					+ "ageofdgpsdata=?, dgpsid = ? " + " WHERE tptid=?";
			PreparedStatement ps = _con.prepareStatement(sql);
			ps.setLong(1, tsid);
			ps.setBigDecimal(2, trkpt.getLat());
			ps.setBigDecimal(3, trkpt.getLon());
			ps.setBigDecimal(4, trkpt.getEle());
			if (trkpt.getTime() != null) {
				java.sql.Timestamp trkptTimestamp = new java.sql.Timestamp(
						trkpt.getTime().getTime());
				ps.setTimestamp(5, trkptTimestamp);
			} else {
				ps.setTimestamp(5, null);
			}
			ps.setBigDecimal(6, trkpt.getMagvar());
			ps.setBigDecimal(7, trkpt.getGeoidheight());
			ps.setString(8, trkpt.getName());
			ps.setString(9, trkpt.getCmt());
			ps.setString(10, trkpt.getType());
			ps.setString(11, trkpt.getDesc());
			ps.setString(12, trkpt.getSrc());
			// link
			ps.setString(13, trkpt.getSym());
			if (trkpt.getFix() != null) {
				ps.setString(14, trkpt.getFix());
			} else {
				ps.setString(14, null);
			}
			ps.setLong(15, trkpt.getSat().longValue());
			ps.setBigDecimal(16, trkpt.getHdop());
			ps.setBigDecimal(17, trkpt.getVdop());
			ps.setBigDecimal(18, trkpt.getPdop());
			ps.setBigDecimal(19, trkpt.getAgeofdgpsdata());
			ps.setLong(20, trkpt.getDgpsid());
			ps.setLong(21, trkpt.getValueObjectId());
			ps.executeUpdate();
			ps.close();

			if (trkpt.getLink() != null) {
				deleteTrkptLinks(trkpt.getValueObjectId());
				insertTrkptLinks(trkpt);
			}
			if (trkpt.getExtensions() != null) {
//				this.deleteTrkptExtensions(trkpt.getValueObjectId());
//				this.insertTrkptExtensions(trkpt.getValueObjectId());
			}
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
