package com.gpxdb.resource;

import java.sql.Connection;
import java.sql.SQLException;

import org.gpxdb.model.*;
import com.gpxdb.dao.*;
import com.gpxdb.dao.mysql.WptMysqlDaoImpl;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/wpt")
public class WptResource extends BaseResource {
	protected WptDao _dao;
	public WptResource() {
		super();
		Connection con = null;
		try {
			con = this.getDS().getConnection();
			_dao = new WptMysqlDaoImpl(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainText() {
		return "Hello from wpt resource";
	}

	/**
	 * Returns the waypoint if existing.
	 * 
	 * @response.representation.200.qname {http://www.gpxdb.com/gpxrs/api/v1/wpt}wptid
	 * @response.representation.200.mediaType application/xml
	 * @response.representation.200.wpt {@link http://www.topografix.com/GPX/1/1#wptType}
	 * 
	 * @return the requested wpt.
	 */
	@GET
	@Path("{wptid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Wpt getById(@PathParam("wptid") long wptid) {
		
		// Note to self: here is a great way to handle the not found exception 
		// using checked exception handling
//		if (bookId != 1) {
//			throw new WebApplicationException(Response.status(
//					Response.Status.INTERNAL_SERVER_ERROR).type(
//					MediaType.TEXT_PLAIN).entity(
//					"Book, " + bookId + ", is not found").build());
//		}
		
		return _dao.findByWptId(wptid);
	}

	// This method is called if HTML is request
	
	@GET
	@Path("{wptid}")
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello(@PathParam("wptid") String wptid) {
		return "<html> " + "<title>" + "Hello Wpt " + wptid + "</title>"
				+ "<body><h1>" + "Hello Wpt:" + wptid + "</h1></body>"
				+ "</html> ";
	}

	@DELETE
	@Path("{wptid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(@PathParam("wptid") Long wptid) {
		_dao.deleteWpt(wptid);
		return;
	}

}
