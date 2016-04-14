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

import com.gpxdb.dao.RteptDao;

import org.gpxdb.model.*;


// TODO: Auto-generated Javadoc
/**
 * The Class RteptMysqlDaoImpl.
 */
public class RteptMysqlDaoImpl implements RteptDao {

	/** The _con. */
	private Connection _con;


	/** The rtept dao. */
	private static RteptMysqlDaoImpl rteptDao = null;


	/**
	 * Gets the rtept dao.
	 *
	 * @return the rtept dao
	 */
	public static RteptDao getRteptDao() {
		if (rteptDao == null) {
			rteptDao = new RteptMysqlDaoImpl();
		}
		return rteptDao;
	}

	/**
	 * Instantiates a new wpt mysql dao impl.
	 */
	public RteptMysqlDaoImpl() {
		super();
	}

	/**
	 * Instantiates a new rtept mysql dao impl.
	 *
	 * @param con the con
	 */
	public RteptMysqlDaoImpl(Connection con) {
		if (_con == null) {
			_con = con;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.RteptDao#deleteRtept(long)
	 */
	public void deleteRtept(long rptid) {
		try {
			CallableStatement cs;
			cs = _con.prepareCall("{call deleteRtept(?)}");
			cs.setLong(1, rptid);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.RteptDao#deleteRteptExtensions(long)
	 */
	public void deleteRteptExtensions(long rptid) {
		try {
			CallableStatement cs;
			cs = _con.prepareCall("{call deleteRteptExtensions(?)}");
			cs.setLong(1, rptid);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.RteptDao#deleteRteptLinks(long)
	 */
	public void deleteRteptLinks(long rptid) {
		try {
			CallableStatement cs;
			cs = _con.prepareCall("{call deleteRteptLinks(?)}");
			cs.setLong(1, rptid);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.RteptDao#findRteptById(long)
	 */
	public Rtept findRteptById(long rptid) {
		Rtept rtept = null;
		String sql = "SELECT * FROM rtept WHERE rptid=" + rptid;
		Statement statement;
		ResultSet rs;
		try {
			statement = _con.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				rtept = getRteptFromResultSet(rs);
			}
			rs.close();
			statement.close();
			if (rtept != null) {
				this.getRteptLinks(rtept);
				// this.getExtensions(rtept);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return rtept;
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
	 * @see org.gpxdb.dao.RteptDao#getTotalCount()
	 */
	public long getTotalCount() {
		long tptcount = 0;
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT count(rptid) AS tptcount FROM rtept");
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
	 * Gets the rtept from result set.
	 *
	 * @param rs the rs
	 * @return the rtept from result set
	 * @throws SQLException the sQL exception
	 */
	private Rtept getRteptFromResultSet(ResultSet rs) throws SQLException {
		Rtept tp = new Rtept();
		tp.setValueObjectId(rs.getLong("rptid"));
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
	 * Gets the rtept links.
	 *
	 * @param rtept the rtept
	 * @return the rtept links
	 * @throws SQLException the sQL exception
	 */
	private void getRteptLinks(Rtept rtept) throws SQLException {
		Link link = null;

		Statement statement = _con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM link "
				+ "WHERE rptid=" + rtept.getValueObjectId());
		while (rs.next()) {
			link = new Link();
			link.setValueObjectId(rs.getLong("linkid"));
			link.setHref(rs.getString("href"));
			link.setText(rs.getString("text"));
			link.setType(rs.getString("type"));
			rtept.getLink().add(link);
		}
		rs.close();
		statement.close();
		return;
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.RteptDao#insert(long, org.gpxdb.model.Rtept)
	 */
	public long insert(long rteid, Rtept rtept) {
		long rptid = 0;
		try {
			String sql = "INSERT INTO rtept "
					+ "(rteid,  lat, lon, ele, time,  magvar, geoidheight, "
					+ " name, cmt, type, `desc`, src, sym, fix, sat, hdop, vdop, pdop, ageofdgpsdata, dgpsid) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = _con.prepareStatement(sql);
			ps.setLong(1, rteid);
			ps.setBigDecimal(2, rtept.getLat());
			ps.setBigDecimal(3, rtept.getLon());
			ps.setBigDecimal(4, rtept.getEle());
			if (rtept.getTime() != null) {
				java.sql.Timestamp rteptTimestamp = new java.sql.Timestamp(
						rtept.getTime().getTime());
				ps.setTimestamp(5, rteptTimestamp);
			} else {
				ps.setTimestamp(5, null);
			}
			ps.setBigDecimal(6, rtept.getMagvar());
			ps.setBigDecimal(7, rtept.getGeoidheight());
			ps.setString(8, rtept.getName());
			ps.setString(9, rtept.getCmt());
			ps.setString(10, rtept.getType());
			ps.setString(11, rtept.getDesc());
			ps.setString(12, rtept.getSrc());
			// link
			ps.setString(13, rtept.getSym());
			if (rtept.getFix() != null) {
				ps.setString(14, rtept.getFix());
			} else {
				ps.setString(14, null);
			}
			ps.setLong(15,rtept.getSat().longValue());
			ps.setBigDecimal(16, rtept.getHdop());
			ps.setBigDecimal(17, rtept.getVdop());
			ps.setBigDecimal(18, rtept.getPdop());
			ps.setBigDecimal(19, rtept.getAgeofdgpsdata());
			ps.setLong(20, rtept.getDgpsid());
			ps.executeUpdate();
			ps.close();
			rptid = this.getLastUpdateId();
			rtept.setValueObjectId(rptid);

			if (rtept.getLink() != null) {
				insertRteptLinks(rtept);
			}
			if (rtept.getExtensions() != null) {
//				insertRteptExtensions(rtept);
			}
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return rptid;
	}

	/**
	 * Insert rtept links.
	 *
	 * @param rtept the rtept
	 * @throws SQLException the exception
	 */
	private void insertRteptLinks(Rtept rtept) throws SQLException {
		long rptid = rtept.getValueObjectId();
		PreparedStatement ps2 = _con.prepareStatement("INSERT INTO link "
				+ "(rptid, href, text, type) VALUES (?, ?, ?, ?)");
		Link link = null;
		for (int i = 0; i < rtept.getLink().size(); i++) {
			link = rtept.getLink().get(i);
			ps2.setLong(1, rptid);
			ps2.setString(2, link.getHref());
			ps2.setString(3, link.getText());
			ps2.setString(4, link.getType() + "");
			ps2.executeUpdate();
		}
		ps2.close();
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.RteptDao#listAllRteptPaged(long, long, String)
	 */
	public ArrayList<Rtept> listAllRteptPaged(long startindex, long limit, String orderby) {
		Rtept rtept;
		ArrayList<Rtept> rteptList = new ArrayList<Rtept>();
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM rtept "
					+ "LIMIT " + startindex + ", " + limit);
			while (rs.next()) {
				rtept = this.getRteptFromResultSet(rs);
				// @TODO extensions
				this.getRteptLinks(rtept);
				rteptList.add(rtept);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return rteptList;
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
	 * @see org.gpxdb.dao.RteptDao#updateRtept(org.gpxdb.model.Rtept)
	 */
	public void updateRtept(Rtept rtept) {
		try {
			long rteid = 0;
			if (rtept.getValueObjectId() == 0) {
				return;
			}
			Statement statement = _con.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT rteid FROM rtept WHERE rptid="
							+ rtept.getValueObjectId());
			while (rs.next()) {
				rteid = rs.getLong("rteid");
			}
			if (rteid == 0) {
				return;
			}
			rs.close();
			statement.close();

			String sql = "UPDATE rtept "
					+ "SET rteid=?, lat=?, lon=?, ele=?, time=?, magvar=?, geoidheight=?, name=?,"
					+ "cmt=?, type=?, `desc`=?, src=?, sym=?, fix=?, sat=?, hdop=?, vdop=?, pdop=?,"
					+ "ageofdgpsdata=?, dgpsid = ? " + " WHERE rptid=?";
			PreparedStatement ps = _con.prepareStatement(sql);
			ps.setLong(1, rteid);
			ps.setBigDecimal(2, rtept.getLat());
			ps.setBigDecimal(3, rtept.getLon());
			ps.setBigDecimal(4, rtept.getEle());
			if (rtept.getTime() != null) {
				java.sql.Timestamp rteptTimestamp = new java.sql.Timestamp(
						rtept.getTime().getTime());
				ps.setTimestamp(5, rteptTimestamp);
			} else {
				ps.setTimestamp(5, null);
			}
			ps.setBigDecimal(6, rtept.getMagvar());
			ps.setBigDecimal(7, rtept.getGeoidheight());
			ps.setString(8, rtept.getName());
			ps.setString(9, rtept.getCmt());
			ps.setString(10, rtept.getType());
			ps.setString(11, rtept.getDesc());
			ps.setString(12, rtept.getSrc());
			// link
			ps.setString(13, rtept.getSym());
			if (rtept.getFix() != null) {
				ps.setString(14, rtept.getFix());
			} else {
				ps.setString(14, null);
			}
			ps.setLong(15, rtept.getSat().longValue());
			ps.setBigDecimal(16, rtept.getHdop());
			ps.setBigDecimal(17, rtept.getVdop());
			ps.setBigDecimal(18, rtept.getPdop());
			ps.setBigDecimal(19, rtept.getAgeofdgpsdata());
			ps.setLong(20, rtept.getDgpsid());
			ps.setLong(21, rtept.getValueObjectId());
			ps.executeUpdate();
			ps.close();

			if (rtept.getLink() != null) {
				deleteRteptLinks(rtept.getValueObjectId());
				insertRteptLinks(rtept);
			}
			if (rtept.getExtensions() != null) {
//				this.deleteRteptExtensions(rtept.getValueObjectId());
//				this.insertRteptExtensions(rtept.getValueObjectId());
			}
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
