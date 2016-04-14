package com.gpxdb.dao.mysql;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;
import com.gpxdb.dao.WptDao;
//import org.gpxdb.dao.WptExtensionDao;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.gpxdb.model.*;
//import com.gpx.model.garmin.WaypointExtension;

// TODO: Auto-generated Javadoc
/**
 * The Class WptMysqlDaoImpl.
 * 
 * @author chris
 */
public class WptMysqlDaoImpl implements WptDao {

	/** The logger. */
//	final Logger logger = LoggerFactory.getLogger(WptMysqlDaoImpl.class);

	/** The _con. */
	private Connection _con;

	/** The wpt dao. */
	private static WptMysqlDaoImpl wptDAO = null;

	/**
	 * Gets the gpx dao.
	 * 
	 * @return the gpx dao
	 */
	public static WptDao getDAO() {
		if (wptDAO == null) {
			wptDAO = new WptMysqlDaoImpl();
		}
		return wptDAO;
	}

	/**
	 * Instantiates a new wpt mysql dao impl.
	 */
	public WptMysqlDaoImpl() {
		super();
	}

	/**
	 * Instantiates a new wpt mysql dao impl.
	 * 
	 * @param con
	 *            the con
	 */
	public WptMysqlDaoImpl(Connection con) {
		if (_con == null) {
			_con = con;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.WptDao#addWpts(org.gpxdb.model.Gpx)
	 */
	public void addWpts(Gpx gpx) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.WptDao#deleteWpt(long)
	 */
	public void deleteWpt(long wptid) {
//		logger.debug("deleteWpt " + wptid);
		try {
			CallableStatement cs;
			cs = _con.prepareCall("{call deleteWpt(?)}");
			cs.setLong(1, wptid);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.WptDao#deleteWptLinks(long)
	 */
	public void deleteWptLinks(long wptid) {
		try {
			CallableStatement cs;
			cs = _con.prepareCall("{call deleteWptLinks(?)}");
			cs.setLong(1, wptid);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.WptDao#deleteWpts(long)
	 */
	public void deleteWpts(long gpxid) {
		PreparedStatement ps;
		try {
			String wptLinksSql = "delete link l from link l where l.wptid in "
					+ "(select w.wptid from wpt w where w.gpxid=?)";
			ps = _con.prepareStatement(wptLinksSql);
			ps.setLong(1, gpxid);
			ps.executeUpdate();
			ps.close();

			String extensionSql = "delete extension e from extension e where e.extid in "
					+ "(select w.extid from wpt w where w.gpxid=?)";
			ps = _con.prepareStatement(extensionSql);
			ps.setLong(1, gpxid);
			ps.execute();
			ps.close();

			String wptSql = "delete wpt from wpt where gpxid=?";
			ps = _con.prepareStatement(wptSql);
			ps.setLong(1, gpxid);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.WptDao#findAll()
	 */
	public ArrayList<Wpt> findAll() {
		Wpt wpt;
		ArrayList<Wpt> wptList = new ArrayList<Wpt>();
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM wpt ");
			while (rs.next()) {
				wpt = this.getWptFromResultSet(rs);
				// @TODO extensions
				this.getWptLinks(wpt);
				wptList.add(wpt);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return wptList;
	}

	/**
	 * Find by gpx id.
	 * 
	 * @param gpxid
	 *            the gpxid
	 * @return the array list
	 */
	public ArrayList<Wpt> findByGpxId(long gpxid) {
		Wpt wpt = null;
		ArrayList<Wpt> wptList = new ArrayList<Wpt>();
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT * FROM wpt WHERE gpxid=" + gpxid);
			while (rs.next()) {
				while (rs.next()) {
					wpt = this.getWptFromResultSet(rs);
					if (rs.getLong("extid") > 0) {
						long extid = rs.getLong("extid");
//						WptExtensionDao dao = new WptExtensionDaoImpl(_con);
//						wpt.setExtensions(dao.findByWaypointExtensionId(extid));
					}
					this.getWptLinks(wpt);
					wptList.add(wpt);
				}
				rs.close();
				statement.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return wptList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.WptDao#findByWptId(long)
	 */
	public Wpt findByWptId(long wptid) {
		Wpt wpt = new Wpt();
		String sql = "SELECT * FROM wpt WHERE wptid = " + wptid;
		Statement statement;
		ResultSet rs;
		try {
			statement = _con.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				wpt = this.getWptFromResultSet(rs);
				if (rs.getLong("extid") > 0) {
					long extid = rs.getLong("extid");
//					WptExtensionDao dao = new WptExtensionDaoImpl(_con);
//					wpt.setExtensions(dao.findByWaypointExtensionId(extid));
				}
				this.getWptLinks(wpt);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return wpt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.WptDao#findByWptsByLoc(java.lang.String)
	 */
	public ArrayList<Wpt> findByWptsByLoc(String location) {
		Wpt wpt;
		ArrayList<Wpt> wptList = new ArrayList<Wpt>();
		try {
			String[] locstr = location.split(",");
			if (locstr.length != 2) {
				return wptList;
			}
			String lat = locstr[0];
			String lon = locstr[1];

			Statement statement = _con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM wpt "
					+ "WHERE lat=" + lat + " AND lon=" + lon);
			while (rs.next()) {
				wpt = this.getWptFromResultSet(rs);
				// @TODO extensions
				this.getWptLinks(wpt);
				wptList.add(wpt);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return wptList;
	}

	/**
	 * Gets the connection.
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		return _con;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.WptDao#getTotalCount()
	 */
	public long getTotalCount() {
		long wptcount = 0;
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT count(wptid) AS wptcount FROM wpt");
			while (rs.next()) {
				wptcount = rs.getLong("wptcount");
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return wptcount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.WptDao#getWpts(org.gpxdb.model.Gpx)
	 */
	public Gpx getWpts(Gpx gpx) {
		Wpt wpt;
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM wpt "
					+ "WHERE gpxid=" + gpx.getValueObjectId());
			while (rs.next()) {
				wpt = this.getWptFromResultSet(rs);
				if (rs.getLong("extid") > 0) {
					long extid = rs.getLong("extid");
//					WptExtensionDao dao = new WptExtensionDaoImpl(_con);
//					wpt.setExtensions(dao.findByWaypointExtensionId(extid));
				}
				this.getWptLinks(wpt);
				gpx.getWpt().add(wpt);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return gpx;
	}

	/**
	 * Insert waypoint.
	 * 
	 * @param gpxid
	 *            the gpxid
	 * @param wpt
	 *            the wpt
	 * @throws SQLException
	 *             the sQL exception
	 */
	public long insertWaypoint(long gpxid, Wpt wpt) {
		long wptid = 0;
		try {
			String sql = "INSERT INTO wpt "
					+ "(gpxid,  lat, lon, ele, time,  magvar, geoidheight, "
					+ " name, cmt, type, `desc`, src, sym, fix, sat, hdop, vdop, pdop, ageofdgpsdata, dgpsid) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = _con.prepareStatement(sql);
			ps.setLong(1, gpxid);
			ps.setBigDecimal(2, wpt.getLat());
			ps.setBigDecimal(3, wpt.getLon());
			ps.setBigDecimal(4, wpt.getEle());
			if (wpt.getTime() != null) {
				java.sql.Timestamp wptTimestamp = new java.sql.Timestamp(wpt
						.getTime().getTime());
				ps.setTimestamp(5, wptTimestamp);
			} else {
				ps.setTimestamp(5, null);
			}
			ps.setBigDecimal(6, wpt.getMagvar());
			ps.setBigDecimal(7, wpt.getGeoidheight());
			ps.setString(8, wpt.getName());
			ps.setString(9, wpt.getCmt());
			ps.setString(10, wpt.getType());
			ps.setString(11, wpt.getDesc());
			ps.setString(12, wpt.getSrc());
			// link
			ps.setString(13, wpt.getSym());
			if (wpt.getFix() != null) {
				ps.setString(14, wpt.getFix());
			} else {
				ps.setString(14, null);
			}
			ps.setLong(15, wpt.getSat().longValue());
			ps.setBigDecimal(16, wpt.getHdop());
			ps.setBigDecimal(17, wpt.getVdop());
			ps.setBigDecimal(18, wpt.getPdop());
			ps.setBigDecimal(19, wpt.getAgeofdgpsdata());
			ps.setLong(20, wpt.getDgpsid());
			ps.executeUpdate();
			ps.close();
			wptid = this.getLastUpdateId();
			wpt.setValueObjectId(wptid);
			
			if (wpt.getLink() != null) {
				insertWptLinks(wpt);
			}
			if (wpt.getExtensions() != null) {
//				for (int i = 0; i < wpt.getExtensions().getAnyObjectCount(); i++) {
//					Object obj = wpt.getExtensions().getAnyObject(i);
//					if (obj instanceof WaypointExtension) {
//						WaypointExtension we = (WaypointExtension) wpt
//								.getExtensions().getAnyObject(i);
//						WptExtensionDao dao = new WptExtensionDaoImpl(_con);
//						dao.addWaypointExtension(wptid, we);
//					}
//				}
			}
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return wptid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.WptDao#findAllDataPaged(long, long)
	 */
	public ArrayList<Wpt> listAllPaged(long start, long limit, String orderby) {
		Wpt wpt;
		ArrayList<Wpt> wptList = new ArrayList<Wpt>();
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM wpt "
					+ "LIMIT " + start + ", " + limit);
			while (rs.next()) {
				wpt = this.getWptFromResultSet(rs);
				// @TODO extensions
				this.getWptLinks(wpt);
				wptList.add(wpt);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return wptList;
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
	 * @see org.gpxdb.dao.WptDao#updateWpt(org.gpxdb.model.Wpt)
	 */
	public Wpt updateWpt(Wpt wpt) {
		Wpt result = new Wpt();
		long wptid = wpt.getValueObjectId();
		if (wptid == 0 || wpt.getLat() == null || wpt.getLon() == null) {
			// must have valid wpt
			return null;
		}

		try {
			String sql = "UPDATE wpt SET ";
			sql += "lat=" + wpt.getLat() + ",";
			sql += "lon=" + wpt.getLon() + ",";
			if (wpt.getEle() != null) {
				sql = sql + " ele=" + wpt.getEle() + ",";
			}
			if (wpt.getTime() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd'T'HH:mm:ss");
				String timestring = sdf.format(wpt.getTime());
				sql = sql + " time=" + timestring + ",";
			}
			if (wpt.getMagvar() != null) {
				sql = sql + " magvar=" + wpt.getMagvar() + ",";
			}
			if (wpt.getGeoidheight() != null) {
				sql = sql + " geoidheight=" + wpt.getGeoidheight() + ",";
			}
			if (wpt.getName() != null) {
				sql = sql + " wpt.name='"
						+ StringEscapeUtils.escapeSql(wpt.getName()) + "',";
			}
			if (wpt.getCmt() != null) {
				sql = sql + " cmt='"
						+ StringEscapeUtils.escapeSql(wpt.getCmt()) + "',";
			}
			if (wpt.getType() != null) {
				sql = sql + " type='" + wpt.getType() + "',";
			}
			if (wpt.getDesc() != null) {
				sql = sql + " wpt.desc='"
						+ StringEscapeUtils.escapeSql(wpt.getDesc()) + "',";
			}
			if (wpt.getSrc() != null) {
				sql = sql + " src='"
						+ StringEscapeUtils.escapeSql(wpt.getSrc()) + "',";
			}
			if (wpt.getSym() != null) {
				sql = sql + " sym='" + wpt.getSym() + "',";
			}
			if (wpt.getSat().longValue() > 0) {
				sql = sql + " sat=" + wpt.getSat() + ",";
			}
			if (wpt.getHdop() != null) {
				sql = sql + " hdop=" + wpt.getHdop() + ",";
			}
			if (wpt.getVdop() != null) {
				sql = sql + " vdop=" + wpt.getVdop() + ",";
			}
			if (wpt.getPdop() != null) {
				sql = sql + " pdop=" + wpt.getPdop() + ",";
			}
			if (wpt.getAgeofdgpsdata() != null) {
				sql = sql + " ageofdgpsdata=" + wpt.getAgeofdgpsdata() + ",";
			}
			if (wpt.getDgpsid() > 0) {
				sql = sql + " dgpsid=" + wpt.getDgpsid() + ",";
			}
			// remove last comma
			sql = sql.substring(0, sql.length() - 1);
			sql = sql + " WHERE wptid=" + wptid;
//			logger.debug("SQL: " + sql);

			Statement s = _con.createStatement();
			s.execute(sql);
			s.close();

			if (wpt.getLink() != null && wpt.getLink().size() > 0) {
				deleteWptLinks(wptid);
				insertWptLinks(wpt);
			}

		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/*
	 * Replaces all waypoints in a gpx.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.WptDao#updateWpts(org.gpxdb.model.Gpx)
	 */
	public void updateWpts(Gpx gpx) {
		if (gpx.getValueObjectId() == 0) {
			return;
		}
		this.deleteWpts(gpx.getValueObjectId());
		this.addWpts(gpx);
	}

	/**
	 * Gets the last update id.
	 * 
	 * @return long LAST_INSERT_ID()
	 * @throws SQLException
	 *             the sQL exception
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

	/**
	 * Gets the wpt from result set.
	 * 
	 * @param rs
	 *            the rs
	 * @return wpt
	 * @throws SQLException
	 *             the sQL exception
	 */
	private Wpt getWptFromResultSet(ResultSet rs) throws SQLException {
		Wpt wpt = new Wpt();
		wpt.setValueObjectId(rs.getLong("wptid"));
		wpt.setLat(rs.getBigDecimal("lat"));
		wpt.setLon(rs.getBigDecimal("lon"));
		wpt.setEle(rs.getBigDecimal("ele"));
		if (rs.getTime("time") != null) {
			wpt.setTime(rs.getTimestamp("time"));
		}
		wpt.setMagvar(rs.getBigDecimal("magvar"));
		wpt.setGeoidheight(rs.getBigDecimal("geoidheight"));
		wpt.setName(rs.getString("name"));
		wpt.setCmt(rs.getString("cmt"));
		wpt.setType(rs.getString("type"));
		wpt.setDesc(rs.getString("desc"));
		wpt.setSrc(rs.getString("src"));
		wpt.setSym(rs.getString("sym"));
		if (rs.getString("fix") != null) {
			wpt.setFix(rs.getString("fix"));
		}
		wpt.setSat(rs.getBigDecimal("sat").toBigInteger() );
		wpt.setHdop(rs.getBigDecimal("hdop"));
		wpt.setVdop(rs.getBigDecimal("vdop"));
		wpt.setPdop(rs.getBigDecimal("pdop"));
		wpt.setAgeofdgpsdata(rs.getBigDecimal("ageofdgpsdata"));
		wpt.setDgpsid(rs.getInt("dgpsid"));
		return wpt;
	}

	/**
	 * Gets the wpt links.
	 * 
	 * @param wpt
	 *            the wpt
	 * @return the wpt links
	 * @throws SQLException
	 *             the sQL exception
	 */
	private void getWptLinks(Wpt wpt) throws SQLException {
		Link link = null;
		Statement statement = _con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM link "
				+ "WHERE wptid=" + wpt.getValueObjectId());
		while (rs.next()) {
			link = new Link();
			link.setValueObjectId(rs.getLong("linkid"));
			link.setHref(rs.getString("href"));
			link.setText(rs.getString("text"));
			link.setType(rs.getString("type"));
			wpt.getLink().add(link);
		}
		rs.close();
		statement.close();
		return;
	}

	private void insertWptLinks(Wpt wpt) throws SQLException {
		long wptid = wpt.getValueObjectId();
		PreparedStatement ps2 = _con.prepareStatement("INSERT INTO link "
				+ "(wptid, href, text, type) VALUES (?, ?, ?, ?)");
		Link link = null;
		for (int i = 0; i < wpt.getLink().size(); i++) {
			link = wpt.getLink().get(i);
			ps2.setLong(1, wptid);
			ps2.setString(2, link.getHref());
			ps2.setString(3, link.getText());
			ps2.setString(4, link.getType() + "");
			ps2.executeUpdate();
		}
		ps2.close();
	}
}
