package com.gpxdb.resource;

import org.gpxdb.model.*;

import com.gpxdb.dao.TrkDao;
import com.gpxdb.dao.mysql.TrkmysqlDaoImpl;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.lang.String;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/trks")
public class TrksResource extends BaseResource {	
	TrkDao _dao;
	
	public TrksResource() {
		super();
		Connection con = null;
		try {
			con = this.getDS().getConnection();
			_dao = new TrkmysqlDaoImpl(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@Path("list")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String listRtes(
			@QueryParam("start") @DefaultValue("0")  int start,
		    @QueryParam("limit") @DefaultValue("10") int limit,
		    @QueryParam("orderby") @DefaultValue("id") String orderby) {
		return "<html> " + "<title>" + "Hello from trks resource." + "</title>"
				+ "<body><h1>" + "Hello from /trks/list." + "</h1>" 
				+ "<p>start=" + start + " limit=" + limit + " orderby=" + orderby + "</p>"
				+ "</body></html>";
	}

	// This method is called if XML is request
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ArrayList<Trk> listAll() {
		return getTrkList();
	}
	
	
	@Path("list")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ArrayList<Trk> listAllPaged(
			@QueryParam("start") @DefaultValue("0")  int start,
		    @QueryParam("limit") @DefaultValue("10") int limit,
		    @QueryParam("orderby") @DefaultValue("id") String orderby) {
		if(orderby!=null){
			// TODO switch this for valid orderby clauses
		}
		return this.getListPaged(start, limit, orderby);
	}
	

	// TODO Return usage description/options link
	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Hello from trksResource" + "</title>"
				+ "<body><h1>" + "Hello TrksResource" + "</body></h1>" + "</html> ";
	}
	
	
	private ArrayList<Trk> getTrkList() {
		ArrayList<Trk> trks;
		trks = this._dao.listAll();
		return trks;
	}
	
	private ArrayList<Trk> getListPaged(long start, long limit, String orderby) {
		ArrayList<Trk> trks;
		trks = this._dao.listAllPaged(start, limit, orderby);
		return trks;
	}
	
}
