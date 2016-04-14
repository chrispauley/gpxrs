package com.gpxdb.dao.mysql;

import org.gpxdb.model.*;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gpxdb.dao.TrkDao;

public class TrkmysqlDaoImpl implements TrkDao {
	private Connection _con;

	private static TrkDao trkDao = null;

	public static TrkDao getTrkDao() {
		if (trkDao == null) {
			trkDao = new TrkmysqlDaoImpl();
		}
		return trkDao;
	}

	public TrkmysqlDaoImpl() {
		super();
	}

	public TrkmysqlDaoImpl(Connection con) {
		super();
		_con = con;
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.TrkDao#addTrk(long, org.gpxdb.model.Trk)
	 */
	public Trk addTrk(long gpxid, Trk trk) {
		Trk result = null;
		try {
			this.saveGpxTrack(gpxid, trk);
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.TrkDao#addTrks(org.gpxdb.model.Gpx)
	 */
	public void addTrks(Gpx gpx) {
		Trk trk;
		long gpxid;
		gpxid = gpx.getValueObjectId();
		try {
			for (int i = 0; i < gpx.getTrk().size(); i++) {
				trk = gpx.getTrk().get(i);
				this.saveGpxTrack(gpxid, trk);
			}
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.TrkDao#deleteTrk(long)
	 */
	public void deleteTrk(long trkid) {
		try {
			CallableStatement cs;
			cs = _con.prepareCall("{call deleteTrk(?)}");
			cs.setLong(1, trkid);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.TrkDao#deleteTrks(long)
	 */
	public void deleteTrks(long gpxid) {
		try {
			CallableStatement cs;
			cs = _con.prepareCall("{call deleteTrks(?)}");
			cs.setLong(1, gpxid);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.TrkDao#findTrkById(long)
	 */
	public Trk findTrkById(long trkid) {
		Trk trk = new Trk();
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM trk "
					+ "WHERE trkid=" + trkid);
			while (rs.next()) {
				trk = this.getTrkFromResultSet(rs);
				getTrkSegments(trk);
				// @TODO extensions
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return trk;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.TrkDao#getTotalCount()
	 */
	public long getTotalCount() {
		long totalcount = 0;
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT count(trkid) AS totalcount FROM trk");
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

	public Trk getTrkById(long trkid) {
		Trk trk = new Trk();
		String sql = "SELECT * FROM trk WHERE trkid=" + trkid;
		Statement statement;
		ResultSet rs;
		try {
			statement = _con.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				trk = this.getTrkFromResultSet(rs);
			}
			rs.close();
			statement.close();

			// Fetch child elements
			getTrkSegments(trk);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return trk;
	}

	public Gpx getTrksForGpx(Gpx gpx) {
		Trk trk;
		String sql = "SELECT * FROM trk WHERE gpxid=" + gpx.getValueObjectId()
				+ " ORDER BY number";
		Statement statement;
		ResultSet rs;
		try {
			statement = _con.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				trk = this.getTrkFromResultSet(rs);
				gpx.getTrk().add(trk);
			}
			rs.close();
			statement.close();

			for (int i = 0; i < gpx.getTrk().size(); i++) {
				trk = gpx.getTrk().get(i);
				getTrkSegments(trk);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return gpx;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.TrkDao#listAll()
	 */
	public ArrayList<Trk> listAll() {
		Trk trk;
		ArrayList<Trk> trklist = new ArrayList<Trk>();
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM trk ");
			while (rs.next()) {
				trk = this.getTrkFromResultSet(rs);
				// @TODO extensions
				// this.getTrkLinks(trk, _con);
				trklist.add(trk);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return trklist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.TrkDao#listAllPaged(long, long)
	 */
	public ArrayList<Trk> listAllPaged(long startindex, long limit, String orderby) {
		Trk trk;
		ArrayList<Trk> trklist = new ArrayList<Trk>();
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM trk "
					+ "LIMIT " + startindex + ", " + limit);
			while (rs.next()) {
				trk = this.getTrkFromResultSet(rs);
				// @TODO extensions
				// this.getTrkLinks(trk);
				trklist.add(trk);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return trklist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.TrkDao#updateTrk(org.gpxdb.model.Trk)
	 */
	public void updateTrk(Trk trk) {
		long gpxid = 0;
		long trkid = trk.getValueObjectId();
		if (trkid == 0) {
			return;
		}
		try {

			Statement statement = _con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT gpxid FROM trk "
					+ "WHERE trkid=" + trkid);
			while (rs.next()) {
				gpxid = rs.getLong("gpxid");
			}
			rs.close();
			statement.close();
			if (gpxid > 0) {
				this.deleteTrk(trkid);
				this.saveGpxTrack(gpxid, trk);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

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

	private Link getLinkFromResultSet(ResultSet rs) throws SQLException {
		Link link = new Link();
		link.setValueObjectId(rs.getLong("linkid"));
		link.setHref(rs.getString("href"));
		link.setText(rs.getString("text"));
		link.setType(rs.getString("type"));
		return link;
	}

	/**
	 * @param rs
	 * @return Trk object
	 * @throws SQLException
	 */
	private Trk getTrkFromResultSet(ResultSet rs) throws SQLException {
		Trk trk = new Trk();
		trk.setValueObjectId(rs.getLong("trkid"));
		trk.setName(rs.getString("name"));
		trk.setCmt(rs.getString("cmt"));
		trk.setDesc(rs.getString("desc"));
		trk.setNumber(BigInteger.valueOf(rs.getLong("number")));
		trk.setType(rs.getString("type"));
		return trk;
	}

	/**
	 * @param trkseg
	 * @throws SQLException
	 */
	private void getTrkPoints(Trkseg trkseg) throws SQLException {
		Trkpt tp;
		String sql = "SELECT * FROM trkpt WHERE tsid="
				+ trkseg.getValueObjectId();
		Statement statement;
		ResultSet rs;
		statement = _con.createStatement();
		rs = statement.executeQuery(sql);
		while (rs.next()) {
			tp = getTrkptFromResultSet(rs);
			// TODO tp.setExtensions();
			trkseg.getTrkpt().add(tp);
		}
		rs.close();
		statement.close();
	}

	/**
	 * @param rs
	 * @return Trkpt
	 * @throws SQLException
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
	 * Routine is to improve performance.
	 * 
	 * @param trkseg
	 * @throws SQLException
	 */
	private void getTrkPtLinks(Trkseg trkseg) throws SQLException {
		Link link;
		Trkpt trkpt;
		Statement statement;
		ResultSet rs;
		String sql;
		long rowcount = 0;
		statement = _con.createStatement();
		rs = statement.executeQuery("SELECT count(*) FROM link l "
				+ "WHERE l.tptid IN " + "(SELECT tpt.tptid FROM trkpt tpt "
				+ "WHERE  tpt.tsid=" + trkseg.getValueObjectId() + " )");
		while (rs.next()) {
			rowcount = rs.getLong(1);
		}
		rs.close();
		statement.close();
		if (rowcount == 0) {
			return;
		}

		sql = "SELECT linkid, href, text, type FROM link l"
				+ " WHERE l.tptid = ?";

		PreparedStatement ps = _con.prepareStatement(sql);
		for (int i = 0; i < trkseg.getTrkpt().size(); i++) {
			trkpt = trkseg.getTrkpt().get(i);
			ps.setLong(1, trkpt.getValueObjectId());
			rs = ps.executeQuery();
			while (rs.next()) {
				link = this.getLinkFromResultSet(rs);
				trkpt.getLink().add(link);
			}
		}
		rs.close();
		ps.close();
	}

	private void getTrkSegments(Trk trk) throws SQLException {
		Trkseg trkseg;

		String sql = "SELECT * FROM trkseg WHERE trkid="
				+ trk.getValueObjectId() + " ORDER BY seqnum";
		Statement statement;
		ResultSet rs;
		statement = _con.createStatement();
		rs = statement.executeQuery(sql);
		while (rs.next()) {
			trkseg = new Trkseg();
			trkseg.setValueObjectId(rs.getLong("tsid"));
			// TODO get extensions
			// trkseg.setExtensions();
			trk.getTrkseg().add(trkseg);
		}
		rs.close();
		statement.close();
		for (int i = 0; i < trk.getTrkseg().size(); i++) {
			trkseg = trk.getTrkseg().get(i);
			getTrkPoints(trkseg);
			getTrkPtLinks(trkseg);
		}
	}

	private void saveGpxTrack(long gpxid, Trk trk) throws SQLException {
		long trkid = 0;
		String sql = "INSERT INTO trk "
				+ "(gpxid, name, cmt, `desc`, number, type) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = _con.prepareStatement(sql);
		ps.setLong(1, gpxid);
		ps.setString(2, trk.getName());
		ps.setString(3, trk.getCmt());
		ps.setString(4, trk.getDesc());
		ps.setLong(5, trk.getNumber().longValue());
		ps.setString(6, trk.getType());
		ps.executeUpdate();
		ps.close();
		trkid = this.getLastUpdateId(_con);

		if (trk.getLink() != null) {
			PreparedStatement ps2 = _con.prepareStatement("INSERT INTO link "
					+ "(trkid, href, text, type) VALUES (?, ?, ?, ?)");
			Link link = null;
			for (int i = 0; i < trk.getLink().size(); i++) {
				link = trk.getLink().get(i);
				ps2.setLong(1, trkid);
				ps2.setString(2, link.getHref());
				ps2.setString(3, link.getText());
				ps2.setString(4, link.getType() + "");
				ps2.executeUpdate();
			}
			ps2.close();
		}
		// Trk-trkseg-trkpt
		if (trk.getTrkseg() != null) {
			Trkseg trkseg;

			PreparedStatement ps3 = _con.prepareStatement("INSERT INTO trkseg "
					+ "(trkid, seqnum) VALUES (?, ?)");
			long tsid = 0;
			for (int i = 0; i < trk.getTrkseg().size(); i++) {
				trkseg = trk.getTrkseg().get(i);
				ps3.setLong(1, trkid);
				ps3.setLong(2, i);
				ps3.executeUpdate();
				tsid = this.getLastUpdateId(_con);
				if (trkseg.getTrkpt() != null) {
					for (int j = 0; j < trkseg.getTrkpt().size(); j++) {
						Trkpt trkpt = trkseg.getTrkpt().get(j);
						this.saveTrkpt(tsid, trkpt);
					}
				}
			}
		}
		return;
	}

	private void saveTrkpt(long tsid, Trkpt trkpt) throws SQLException {
		long tptid = 0;

		String sql = "INSERT INTO trkpt "
				+ "(tsid, lat, lon, ele, time, magvar, geoidheight, "
				+ " name, cmt, type, `desc`, src, sym, fix, sat, hdop, vdop, pdop, ageofdgpsdata, dgpsid) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = _con.prepareStatement(sql);
		ps.setLong(1, tsid);
		ps.setBigDecimal(2, trkpt.getLat());
		ps.setBigDecimal(3, trkpt.getLon());
		ps.setBigDecimal(4, trkpt.getEle());
		if (trkpt.getTime() != null) {
			java.sql.Timestamp trkptTimestamp = new java.sql.Timestamp(trkpt
					.getTime().getTime());
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
		tptid = this.getLastUpdateId(_con);

		if (trkpt.getLink() != null) {
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
		return;
	}

}
