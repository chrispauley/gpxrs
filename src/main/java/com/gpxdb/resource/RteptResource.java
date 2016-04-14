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

@Path("/rtept")
public class RteptResource extends BaseResource {
	protected RteptDao _dao;
	public RteptResource() {
		super();
		Connection con = null;
		try {
			con = this.getDS().getConnection();
			_dao = new RteptMysqlDaoImpl(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainText() {
		return "Hello from rtept resource";
	}

	/**
	 * Returns the route point if existing.
	 * 
	 * @response.representation.200.qname {http://www.gpxdb.com/gpxrs/api/v1/rtept}rteptid
	 * @response.representation.200.mediaType application/xml
	 * @response.representation.200.rtept {@link http://www.topografix.com/GPX/1/1#rteptType}
	 * 
	 * @return the requested rtept.
	 */
	@GET
	@Path("{rptid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Rtept getById(@PathParam("rptid") long rptid) {
		return _dao.findRteptById(rptid);
	}

	// This method is called if HTML is request
	
	@GET
	@Path("{rptid}")
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello(@PathParam("wptid") String rptid) {
		return "<html> " + "<title>" + "Hello Wpt " + rptid + "</title>"
				+ "<body><h1>" + "Hello Wpt:" + rptid + "</h1></body>"
				+ "</html> ";
	}

	@DELETE
	@Path("{rptid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(@PathParam("rptid") Long rptid) {
		_dao.deleteRtept(rptid);
		return;
	}

}
