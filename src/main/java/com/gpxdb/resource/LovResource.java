package com.gpxdb.resource;

import com.gpxdb.model.lov.*;
import com.gpxdb.dao.mysql.LovmysqlDaoImpl;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/lov")
public class LovResource {	
	private final static String DEFAULT_DATASOURCE_NAME = "jdbc/gpxdb";
	private String _datasourceName;
	private DataSource _dataSource;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello from lov resource updated.";
	}


	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		System.out.println("1");
		_dataSource = this.getDS();
		try {
			Connection con = _dataSource.getConnection();
			LovmysqlDaoImpl dao = new LovmysqlDaoImpl(con);
			ArrayList<LovLink> links = dao.getLovLinks();
			
			return "success";
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

		return "<html> " + "<title>" + "Hello Jersey" + "</title>"
				+ "<body><h1>" + "Hello from lov resource html"  
				+ this.getSample().toString() +
				"</body></h1>" + "</html> ";
	}
	
	// This method is called if XML is request
	@GET
	@Produces({ MediaType.TEXT_XML})
	public String sayTextXML() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello from LovResource" + "</hello>";
	}
	
	private LovLink getSample() {
		LovLink lov = new LovLink();
		lov.setHref("http://google.com");
		lov.setText("google");
		lov.setTitle("Title for google link");
		return lov;
	}
	
	private DataSource getDS(){
		if (_dataSource == null) {
			if(this._datasourceName == null){
				this._datasourceName = DEFAULT_DATASOURCE_NAME;
			}

			try {
				Context env = (Context) new InitialContext()
				.lookup("java:comp/env");
				_dataSource = (DataSource) env.lookup(_datasourceName);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return _dataSource;
	}
	
}
