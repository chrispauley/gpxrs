package com.gpxdb.dao.mysql;

import org.gpxdb.model.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;

import com.gpxdb.dao.*;

public class RtemysqlDaoImpl implements RteDao {
	private Connection _con;

	private static RteDao rteDao = null;

	public static RteDao getRteDao() {
		if (rteDao == null) {
			rteDao = new RtemysqlDaoImpl();
		}
		return rteDao;
	}

	public RtemysqlDaoImpl() {
		super();
	}

	public RtemysqlDaoImpl(Connection con) {
		super();
		_con = con;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.RteDao#addRtes(org.gpxdb.model.Gpx)
	 */
	public void addRtes(Gpx gpx) {
		Rte rte;
		long gpxid;
		try {
			gpxid = gpx.getValueObjectId();
			for (int i = 0; i < gpx.getRte().size(); i++) {
				rte = gpx.getRte().get(i);
				this.insertGpxRoute(gpxid, rte);
			}
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.RteDao#deleteRte(long)
	 */
	public void deleteRte(long rteid) {
		try {
			CallableStatement cs;
			cs = _con.prepareCall("{call deleteRte(?)}");
			cs.setLong(1, rteid);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.gpxdb.dao.RteDao#deleteRtes(long)
	 */
	public void deleteRtes(long gpxid) {
		try {
			CallableStatement cs;
			cs = _con.prepareCall("{call deleteRtes(?)}");
			cs.setLong(1, gpxid);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Rte getRteById(long rteid) {
		System.out.println("findRteById");
		Rte rte = new Rte();
		String sql = "SELECT * FROM rte WHERE rteid=" + rteid;
		Statement statement;
		ResultSet rs;
		try {
			statement = _con.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				rte = this.getRteFromResultSet(rs);
			}
			rs.close();
			statement.close();
			// Fetch the links
			this.getRteLinks(rte);

			// Fetch child elements
			getRtepts(rte);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return rte;
	}

	public Rtept getRteptById(long rptid) {
		Rtept rtept = new Rtept();
		Statement statement;
		ResultSet rs;
		String sql;

		try {
			statement = _con.createStatement();
			sql = "SELECT * FROM rtept WHERE rptid=" + rptid;
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				rtept = getRteptFromResultSet(rs);
				// TODO rp.setExtensions();
			}
			rs.close();
			statement.close();
			// Fetch the routing point links
			getRteptLinks(rtept);
			// TODO fetch rtept extensions
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return rtept;
	}

	/**
	 * @param gpx
	 * @throws SQLException
	 */
	public void getRtepts(Gpx gpx) throws SQLException {
		Rte rte;
		Rtept rp;
		Statement statement;
		ResultSet rs;
		String sql;

		statement = _con.createStatement();
		for (int i = 0; i < gpx.getRte().size(); i++) {
			rte = gpx.getRte().get(i);
			sql = "SELECT * FROM rtept WHERE rteid=" + rte.getValueObjectId();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				rp = getRteptFromResultSet(rs);
				// TODO rp.setExtensions();
				rte.getRtept().add(rp);
			}
			rs.close();
		}
		statement.close();
		// Fetch the routing point links
		getRtePtLinks(gpx);
	}

	/**
	 * @param rte
	 * @throws SQLException
	 */
	public void getRtepts(Rte rte) throws SQLException {
		Rtept rtept;
		Statement statement;
		ResultSet rs;
		String sql;

		statement = _con.createStatement();

		sql = "SELECT * FROM rtept WHERE rteid=" + rte.getValueObjectId();
		rs = statement.executeQuery(sql);
		while (rs.next()) {
			rtept = getRteptFromResultSet(rs);
			// TODO rp.setExtensions();
			rte.getRtept().add(rtept);
		}
		rs.close();
		statement.close();
		// Fetch the routing point links
		getRteptLinks(rte);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.RteDao#getRtes(org.gpxdb.model.Gpx)
	 */
	public Gpx getRtes(Gpx gpx) {
		Rte rte;
		Link link;
		PreparedStatement ps;
		Statement statement;
		ResultSet rs;
		String sql = "SELECT * FROM rte WHERE gpxid=" + gpx.getValueObjectId();
		try {
			statement = _con.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				rte = this.getRteFromResultSet(rs);
				gpx.getRte().add(rte);
			}
			rs.close();
			statement.close();
			ps = _con.prepareStatement("SELECT * FROM link WHERE rteid=?");
			for (int i = 0; i < gpx.getRte().size(); i++) {
				rte = gpx.getRte().get(i);
				ps.setLong(1, rte.getValueObjectId());
				rs = ps.executeQuery();
				while (rs.next()) {
					link = getLinkFromResultSet(rs);
					rte.getLink().add(link);
				}
				rs.close();
			}
			getRtepts(gpx);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return gpx;
	}

	/**
	 * Get total count returns the total count of rows in the rte table.
	 * @return long
	 */
	public long getTotalCount() {
		long totalcount = 0;
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT count(rteid) AS totalcount FROM rte");
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

	/**
	 * Inserts a row in the rte table and associates it to the gpx via gpxid.
	 * @param gpxid
	 * @param rte
	 * @throws SQLException
	 */
	public long insertGpxRoute(long gpxid, Rte rte) {
		long rteid = 0;
		String sql = "INSERT INTO rte "
				+ "(gpxid, name, cmt, `desc`, number, type) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = _con.prepareStatement(sql);
			ps.setLong(1, gpxid);
			ps.setString(2, rte.getName());
			ps.setString(3, rte.getCmt());
			ps.setString(4, rte.getDesc());
			ps.setLong(5, rte.getNumber());
			ps.setString(6, rte.getType());
			ps.executeUpdate();

			rteid = this.getLastUpdateId();
			rte.setValueObjectId(rteid);

			if (rte.getLink() != null) {
				PreparedStatement ps2 = _con
						.prepareStatement("INSERT INTO link "
								+ "(rteid, href, text, type) VALUES (?, ?, ?, ?)");
				Link link = null;
				for (int i = 0; i < rte.getLink().size(); i++) {
					link = rte.getLink().get(i);
					ps2.setLong(1, rteid);
					ps2.setString(2, link.getHref());
					ps2.setString(3, link.getText());
					ps2.setString(4, link.getType() + "");
					ps2.executeUpdate();
				}
				ps2.close();
			}
			// Save the routing Points
			if (rte.getRtept() != null) {
				RteptDao dao = new RteptMysqlDaoImpl(this._con);
				for (int i = 0; i < rte.getRtept().size(); i++) {	
					Rtept rtept = (Rtept) rte.getRtept().get(i);
					dao.insert(rteid, rtept);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return rteid;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.RteDao#listAll()
	 */
	public ArrayList<Rte> listAll() {
		Rte rte;
		ArrayList<Rte> rtelist = new ArrayList<Rte>();

		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM rte ");
			while (rs.next()) {
				rte = this.getRteFromResultSet(rs);
				// @TODO extensions
				this.getRteLinks(rte);
				rtelist.add(rte);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return rtelist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gpxdb.dao.RteDao#listAllPaged(long, long)
	 */
	public ArrayList<Rte> listAllPaged(long startindex, long limit, String orderby) {
		Rte rte;
		ArrayList<Rte> rtelist = new ArrayList<Rte>();
		try {
			Statement statement = _con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM rte "
					+ "LIMIT " + startindex + ", " + limit);
			while (rs.next()) {
				rte = this.getRteFromResultSet(rs);
				// @TODO extensions
				this.getRteLinks(rte);
				rtelist.add(rte);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return rtelist;
	}

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

	private Link getLinkFromResultSet(ResultSet rs) throws SQLException {
		Link link = new Link();
		link.setValueObjectId(rs.getLong("linkid"));
		link.setHref(rs.getString("href"));
		link.setText(rs.getString("text"));
		link.setType(rs.getString("type"));
		return link;
	}

	private Rte getRteFromResultSet(ResultSet rs) throws SQLException {
		Rte rte = new Rte();
		rte.setValueObjectId(rs.getLong("rteid"));
		rte.setName(rs.getString("name"));
		rte.setCmt(rs.getString("cmt"));
		rte.setDesc(rs.getString("desc"));
		rte.setNumber(rs.getLong("number"));
		rte.setType(rs.getString("type"));
		return rte;
	}

	private void getRteLinks(Rte rte) throws SQLException {
		Link link = null;
		Statement statement = _con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM link "
				+ "WHERE rteid=" + rte.getValueObjectId());
		while (rs.next()) {
			link = getLinkFromResultSet(rs);
			rte.getLink().add(link);
		}
		rs.close();
		statement.close();
		return;
	}

	private Rtept getRteptFromResultSet(ResultSet rs) throws SQLException {
		Rtept rp = new Rtept();
		rp.setValueObjectId(rs.getLong("rptid"));
		rp.setLat(rs.getBigDecimal("lat"));
		rp.setLon(rs.getBigDecimal("lon"));
		rp.setEle(rs.getBigDecimal("ele"));
		if (rs.getTime("time") != null) {
			rp.setTime(rs.getTimestamp("time"));
		}
		rp.setMagvar(rs.getBigDecimal("magvar"));
		rp.setGeoidheight(rs.getBigDecimal("geoidheight"));
		rp.setName(rs.getString("name"));
		rp.setCmt(rs.getString("cmt"));
		rp.setType(rs.getString("type"));
		rp.setDesc(rs.getString("desc"));
		rp.setSrc(rs.getString("src"));
		rp.setSym(rs.getString("sym"));
		if (rs.getString("fix") != null) {
			rp.setFix(rs.getString("fix"));
		}
		rp.setSat(BigInteger.valueOf(rs.getLong("sat")));
		rp.setHdop(rs.getBigDecimal("hdop"));
		rp.setVdop(rs.getBigDecimal("vdop"));
		rp.setPdop(rs.getBigDecimal("pdop"));
		rp.setAgeofdgpsdata(rs.getBigDecimal("ageofdgpsdata"));
		rp.setDgpsid(rs.getInt("dgpsid"));
		return rp;
	}

	/**
	 * Optimized.
	 * 
	 * @param rte
	 * @throws SQLException
	 */
	private void getRteptLinks(Rte rte) throws SQLException {
		Rtept rtept;
		Link link;
		ResultSet rs;
		String sql;

		sql = "SELECT linkid, href, text, type FROM link l"
				+ " WHERE l.rptid = ?";

		PreparedStatement ps = _con.prepareStatement(sql);

		for (int j = 0; j < rte.getRtept().size(); j++) {
			rtept = rte.getRtept().get(j);
			ps.setLong(1, rtept.getValueObjectId());
			rs = ps.executeQuery();
			while (rs.next()) {
				link = getLinkFromResultSet(rs);
				rtept.getLink().add(link);
			}
		}
	}

	private void getRteptLinks(Rtept rtept) throws SQLException {
		Link link;
		ResultSet rs;
		String sql;

		sql = "SELECT linkid, href, text, type FROM link l"
				+ " WHERE l.rptid = ?";

		PreparedStatement ps = _con.prepareStatement(sql);
		ps.setLong(1, rtept.getValueObjectId());
		rs = ps.executeQuery();
		while (rs.next()) {
			link = getLinkFromResultSet(rs);
			rtept.getLink().add(link);
		}
		rs.close();
		ps.close();
	}

	/**
	 * Optimized.
	 * 
	 * @param gpx
	 * @throws SQLException
	 */
	private void getRtePtLinks(Gpx gpx) throws SQLException {
		Rte rte;
		Rtept rtept;
		Link link;
		ResultSet rs;
		String sql;

		sql = "SELECT linkid, href, text, type FROM link l"
				+ " WHERE l.rptid = ?";

		PreparedStatement ps = _con.prepareStatement(sql);
		for (int i = 0; i < gpx.getRte().size(); i++) {
			rte = gpx.getRte().get(i);
			for (int j = 0; j < rte.getRtept().size(); j++) {
				rtept = rte.getRtept().get(j);
				ps.setLong(1, rtept.getValueObjectId());
				rs = ps.executeQuery();
				while (rs.next()) {
					link = getLinkFromResultSet(rs);
					rtept.getLink().add(link);
				}
			}
		}
	}


}
