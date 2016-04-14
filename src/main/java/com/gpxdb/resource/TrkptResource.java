package com.gpxdb.resource;

import java.sql.Connection;
import java.sql.SQLException;

import org.gpxdb.model.*;
import com.gpxdb.dao.*;
import com.gpxdb.dao.mysql.*;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/trkpt")
public class TrkptResource extends BaseResource {
	protected TrkptDao _dao;
	public TrkptResource() {
		super();
		Connection con = null;
		try {
			con = this.getDS().getConnection();
			_dao = new TrkptMysqlDaoImpl(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainText() {
		return "Hello from trkpt resource";
	}

	/**
	 * Returns the track point if existing.
	 * 
	 * @response.representation.200.qname {http://www.gpxdb.com/gpxrs/api/v1/trkpt}trkptid
	 * @response.representation.200.mediaType application/xml
	 * @response.representation.200.trkpt {@link http://www.topografix.com/GPX/1/1#trkptType}
	 * 
	 * @return the requested trkpt.
	 */
	@GET
	@Path("{tptid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Trkpt getById(@PathParam("tptid") long tptid) {
		return _dao.findTrkptById(tptid);
	}

	// This method is called if HTML is request
	
	@GET
	@Path("{tptid}")
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello(@PathParam("wptid") String tptid) {
		return "<html> " + "<title>" + "Hello Trkpt " + tptid + "</title>"
				+ "<body><h1>" + "Hello Trkpt:" + tptid + "</h1></body>"
				+ "</html> ";
	}

	@DELETE
	@Path("{tptid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(@PathParam("tptid") Long tptid) {
		_dao.deleteTrkpt(tptid);
		return;
	}

}
