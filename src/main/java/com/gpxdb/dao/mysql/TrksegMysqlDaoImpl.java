package com.gpxdb.dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gpxdb.dao.TrkptDao;
import com.gpxdb.dao.TrksegDao;

import org.gpxdb.model.*;


// TODO: Auto-generated Javadoc
/**
 * The Class TrksegMysqlDaoImpl.
 */
public class TrksegMysqlDaoImpl implements TrksegDao {

	/** The _con. */
	private Connection _con;


	/** The trkseg dao. */
	private static TrksegMysqlDaoImpl trksegDao = null;


	/**
	 * Gets the trkseg dao.
	 *
	 * @return the trkseg dao
	 */
	public static TrksegDao getTrksegDao() {
		if (trksegDao == null) {
			trksegDao = new TrksegMysqlDaoImpl();
		}
		return trksegDao;
	}

	/**
	 * Instantiates a new wpt mysql dao impl.
	 */
	public TrksegMysqlDaoImpl() {
		super();
	}

	/**
	 * Instantiates a new trkseg mysql dao impl.
	 *
	 * @param con the con
	 */
	public TrksegMysqlDaoImpl(Connection con) {
		if (_con == null) {
			_con = con;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.TrksegDao#deleteTrkseg(long)
	 */
	public void deleteTrkseg(long tsid) {
		try {
			CallableStatement cs;
			cs = _con.prepareCall("{call deleteTrkseg(?)}");
			cs.setLong(1, tsid);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.TrksegDao#deleteTrksegExtensions(long)
	 */
	public void deleteTrksegExtensions(long tsid) {
		try {
			CallableStatement cs;
			cs = _con.prepareCall("{call deleteTrksegExtensions(?)}");
			cs.setLong(1, tsid);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.TrksegDao#findTrksegById(long)
	 */
	public Trkseg findTrksegById(long tsid) {
		Trkseg trkseg = null;
		String sql = "SELECT * FROM trkseg WHERE tsid=" + tsid;
		Statement statement;
		ResultSet rs;
		try {
			statement = _con.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				trkseg = getTrksegFromResultSet(rs);
			}
			rs.close();
			statement.close();
			if (trkseg != null) {
				// this.getExtensions(trkseg);
				TrkptDao trkptdao = new TrkptMysqlDaoImpl(this._con);
				trkseg.getTrkpt().addAll(
				trkptdao.getTrkptsByTrksegId(tsid));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return trkseg;
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
	 * @see org.gpxdb.dao.TrksegDao#getTotalCount()
	 */
	public long getTotalCount() {
		long tptcount = 0;
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT count(tsid) AS tptcount FROM trkseg");
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

	/**
	 * Gets the trkseg from result set.
	 *
	 * @param rs the rs
	 * @return the trkseg from result set
	 * @throws SQLException the sQL exception
	 */
	private Trkseg getTrksegFromResultSet(ResultSet rs) throws SQLException {
		Trkseg trkseg = new Trkseg();
		trkseg.setValueObjectId(rs.getLong("tsid"));
		return trkseg;
	}


	/* (non-Javadoc)
	 * @see org.gpxdb.dao.TrksegDao#insert(long, org.gpxdb.model.Trkseg)
	 */
	public long insert(long trkid, Trkseg trkseg) {
		long tsig = 0;
		try {
			String sql = "INSERT INTO trkseg "
					+ "(trkid) "
					+ "VALUES (?)";
			PreparedStatement ps = _con.prepareStatement(sql);
			ps.setLong(1, trkid);
			ps.executeUpdate();
			ps.close();
			tsig = this.getLastUpdateId();
			trkseg.setValueObjectId(tsig);

			if (trkseg.getExtensions() != null) {
//				insertTrksegExtensions(trkseg);
			}
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return tsig;
	}


	/* (non-Javadoc)
	 * @see org.gpxdb.dao.TrksegDao#listAllTrksegPaged(long, long)
	 */
	public ArrayList<Trkseg> listAllTrksegPaged(long startindex, long limit, String orderby) {
		Trkseg trkseg;
		ArrayList<Trkseg> trksegList = new ArrayList<Trkseg>();
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM trkseg "
					+ "LIMIT " + startindex + ", " + limit);
			while (rs.next()) {
				trkseg = this.getTrksegFromResultSet(rs);
				// @TODO extensions
				trksegList.add(trkseg);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return trksegList;
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
	 * @see org.gpxdb.dao.TrksegDao#updateTrkseg(org.gpxdb.model.Trkseg)
	 */
	public void updateTrkseg(Trkseg trkseg) {
		try {
			long trkid = 0;
			if (trkseg.getValueObjectId() == 0) {
				return;
			}
			Statement statement = _con.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT trkid FROM trkseg WHERE tsid="
							+ trkseg.getValueObjectId());
			while (rs.next()) {
				trkid = rs.getLong("trkid");
			}
			if (trkid == 0) {
				return;
			}
			rs.close();
			statement.close();

			String sql = "UPDATE trkseg "
					+ "SET trkid=? WHERE tsid=?";
			PreparedStatement ps = _con.prepareStatement(sql);
			ps.setLong(1, trkid);
			ps.setLong(2, trkseg.getValueObjectId());
			ps.executeUpdate();
			ps.close();

			if (trkseg.getExtensions() != null) {
//				this.deleteTrksegExtensions(trkseg.getValueObjectId());
//				this.insertTrksegExtensions(trkseg.getValueObjectId());
			}
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
