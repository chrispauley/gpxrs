package com.gpxdb.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.gpxdb.model.*;
import com.gpxdb.dao.*;
import com.gpxdb.model.lov.LovLink;

import org.gpxdb.model.*;

public class LovmysqlDaoImpl implements LovDao {
//	final Logger logger = LoggerFactory.getLogger(LovmysqlDaoImpl.class);
	// singleton DAO
	private static LovmysqlDaoImpl lovdao = null;
	private Connection connection;
	
	public static LovDao getLinkDAO() {
		if (lovdao == null) {
			lovdao = new LovmysqlDaoImpl();
		}
		return lovdao;
	}
	
	public LovmysqlDaoImpl() {
		super();
	}

	public LovmysqlDaoImpl(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public Link create(Link link) throws SQLException {
//		logger.debug("create");
		if(link==null){
			return null;
		}
		Statement statement = null;
		ResultSet resultset = null;
	    PreparedStatement ps = null;
	     
	    try {
			ps = connection.prepareStatement(
		  	"INSERT INTO link (href, text, type) VALUES (?, ?, ?)");
			ps.setString(1, link.getHref());
			ps.setString(2, link.getText());
			ps.setString(3, link.getType());
			ps.execute();
			ps.close();
			
			// Fetch that id
			String query = "SELECT LAST_INSERT_ID()";
			statement = connection.createStatement();
			resultset = statement.executeQuery(query);
			while(resultset.next()){
//				logger.debug("New row:" + resultset.getInt(1));
				//link.setLinkid(resultset.getInt(1));
			}
			resultset.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			this.connection.close();
		}
		return link;
	}

	public void delete(Link link) {
//		logger.debug("delete");
		if(link==null){
			return;
		}
	    PreparedStatement ps = null;     
	    try {
			ps = connection.prepareStatement(
		  	"DELETE FROM link WHERE href=? and text=? and type=?");
			ps.setString(1, link.getHref());
			ps.setString(2, link.getText());
			ps.setString(3, link.getType());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}

	public Link update(Link link) {
//		logger.debug("update");
		if(link==null){
			return null;
		}
	    PreparedStatement ps = null;
	     
	    try {
			ps = connection.prepareStatement(
		  	"UPDATE link SET href=?, text=?, type=? WHERE linkid=?");
			ps.setString(1, link.getHref());
			ps.setString(2, link.getText());
			ps.setString(3, link.getType());
			ps.setLong(4, link.getValueObjectId());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		return link;
	}

	public LovLink findById(long id) {
//		logger.debug("findById");		
		Statement statement = null;
		ResultSet rs = null;
		LovLink link = new LovLink();
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("Select * from link "
					+ "where linkid="+id);
			while (rs.next()) {
				link.setValueObjectId(rs.getLong("linkid"));
				link.setHref(rs.getString("href"));
				link.setText(rs.getString("text"));
				link.setType(rs.getString("type"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return link;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector findall() {
//		logger.debug("findall");
		Vector result = new Vector();
		Statement statement = null;
		ResultSet rs = null;	
		Link link = null;
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("Select * from link");
			while (rs.next()) {
				link = new Link();
				link = loadFromResultSet(link, rs);
				result.add((Link) link);
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
//		logger.debug("return");
		return result;		
	}
	
	private Link loadFromResultSet(Link link, ResultSet rs) throws SQLException{
		link.setValueObjectId(rs.getLong("linkid"));				
		link.setHref(rs.getString("href"));
		link.setText(rs.getString("text"));
		link.setType(rs.getString("type"));		
		return link;
	}

	private LovLink loadLovLinkFromResultSet(ResultSet rs) throws SQLException{
		LovLink lovlink = new LovLink();
		lovlink.setLovLinkid(rs.getLong("lovid"));				
		lovlink.setHref(rs.getString("href"));
		lovlink.setTitle(rs.getString("title"));
		lovlink.setLang(rs.getString("lang"));		
		lovlink.setText(rs.getString("text"));
		lovlink.setType(rs.getString("type"));
		return lovlink;
	}

	public ArrayList<LovLink> getLovLinks() {
		LovLink lovlink = null;
//		logger.debug("getLovLinks");
		ArrayList<LovLink> result = new ArrayList<LovLink>();
		Statement statement = null;
		ResultSet rs = null;	
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT lovid, title, lang, href, text, type "
						+ " FROM lov_link");
			while (rs.next()) {
				lovlink = loadLovLinkFromResultSet(rs);
				result.add(lovlink);
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return result;				
	}

}
