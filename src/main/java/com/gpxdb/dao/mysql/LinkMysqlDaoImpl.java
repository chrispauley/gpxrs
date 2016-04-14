package com.gpxdb.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;

import org.gpxdb.model.Link;

public class LinkMysqlDaoImpl implements com.gpxdb.dao.LinkDao {

	private LinkMysqlDaoImpl _dao = null;
	private Connection connection = null;

	public LinkMysqlDaoImpl() {
		super();
	}

	public LinkMysqlDaoImpl(Connection connection) {
		super();
		if (_dao == null) {
			_dao = new LinkMysqlDaoImpl();
			this.connection = connection;
		}
	}

	public long addLinkToMetadata(long metid, Link link) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long addLinkToPerson(long perid, Link link) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public long addLinkToRte(long rteid, Link link) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public long addLinkToRtept(long rptid, Link link) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public long addLinkToTrk(long trkid, Link link) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public long addLinkToTrkpt(long tptid, Link link) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public long addLinkToWpt(long wptid, Link link) {
		if (link == null) {
			return 0;
		}
		long linkid = 0;
		Statement statement = null;
		ResultSet resultset = null;
		PreparedStatement ps = null;

		try {
			ps = connection
					.prepareStatement("INSERT INTO link (wptid, href, text, type) VALUES (?, ?, ?, ?)");
			ps.setLong(1, wptid);
			ps.setString(2, link.getHref());
			ps.setString(3, link.getText());
			ps.setString(4, link.getType());
			ps.execute();
			ps.close();
			String query = "SELECT LAST_INSERT_ID()";
			statement = connection.createStatement();
			resultset = statement.executeQuery(query);
			while (resultset.next()) {
				linkid = resultset.getLong(1);
			}
			resultset.close();
			this.connection.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return linkid;
	}

	
	public void delete(long linkid) {
		try {
			String sql = "delete link from link where linkid=?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, linkid);
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	public Link findById(long linkid) {
		Link link = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("Select * from link " + "where linkid="
					+ linkid);
			while (rs.next()) {
				link = new Link();
				link.setValueObjectId(rs.getLong("linkid"));
				link.setHref(rs.getString("href"));
				link.setText(rs.getString("text"));
				link.setType(rs.getString("type"));
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return link;
	}

	/**
	 * Get total count of the links from the link table. Used for pagination.
	 * 
	 * @return long
	 */
	
	public long getTotalCount() {
		long totalcount = 0;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT count(linkid) AS totalcount FROM link");
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

	
	public ArrayList<Link> listAll() {
		ArrayList<Link> linkList = new ArrayList<Link>();
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM link ";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				linkList.add(this.getLinkFromResultset(rs));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return linkList;
	}

	
	public ArrayList<Link> listAllPaged(long start, long offset, String orderby) {
		ArrayList<Link> linkList = new ArrayList<Link>();
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM link " + "LIMIT " + start + ", "
					+ offset;
			if (orderby != null && orderby.length() > 0) {
				if (orderby.equalsIgnoreCase("type")) {
					sql += " ORDERBY " + orderby;
				} else if (orderby.equalsIgnoreCase("text")) {
					sql += " ORDERBY " + orderby;
				}
			}
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				linkList.add(this.getLinkFromResultset(rs));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return linkList;
	}

	
	public void update(Link link) {
		// TODO Auto-generated method stub

	}
	/**
	 * Internal routine to fetch a link object from a row.
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Link getLinkFromResultset(ResultSet rs) throws SQLException {
		Link link = new Link();
		link.setHref(rs.getString("href"));
		link.setText("text");
		link.setType(rs.getString("type"));
		link.setValueObjectId(rs.getLong("linkid"));
		return link;
	}	

}
