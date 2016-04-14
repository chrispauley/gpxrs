package com.gpxdb.resource;

import java.sql.Connection;
import java.sql.SQLException;

import org.gpxdb.model.*;
import com.gpxdb.dao.*;
import com.gpxdb.dao.mysql.TrkmysqlDaoImpl;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/trk")
public class TrkResource extends BaseResource {
	protected TrkDao _dao;
	public TrkResource() {
		super();
		Connection con = null;
		try {
			con = this.getDS().getConnection();
			_dao = new TrkmysqlDaoImpl(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainText() {
		return "Hello from trk resource";
	}

	/**
	 * Returns the rte if existing.
	 * 
	 * @response.representation.200.qname {http://www.gpxdb.com/gpxrs/api/v1/rte}trkid
	 * @response.representation.200.mediaType application/xml
	 * @response.representation.200.rte {@link http://www.topografix.com/GPX/1/1#rteType}
	 * 
	 * @return the requested rte.
	 */
	@GET
	@Path("{trkid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Trk getTrkById(@PathParam("trkid") long trkid) {
		return _dao.getTrkById(trkid);
	}

	// This method is called if HTML is request
	
	@GET
	@Path("{trkid}")
	@Produces(MediaType.TEXT_HTML)
	public String getTrkByIdHtml(@PathParam("trkid") String trkid) {
		return "<html> " + "<title>" + "Hello Trk " + trkid + "</title>"
				+ "<body><h1>" + "Hello Trk:" + trkid + "</h1></body>"
				+ "</html> ";
	}

	@DELETE
	@Path("{trkid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void removeTrkpt(@PathParam("trkid") Long trkid) {
		_dao.deleteTrk(trkid);
		return;
	}
	
}
