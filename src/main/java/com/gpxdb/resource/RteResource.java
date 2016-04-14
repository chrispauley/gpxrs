package com.gpxdb.resource;

import java.sql.Connection;
import java.sql.SQLException;

import org.gpxdb.model.*;
import com.gpxdb.dao.*;
import com.gpxdb.dao.mysql.RtemysqlDaoImpl;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/rte")
public class RteResource extends BaseResource {
	protected RteDao _dao;
	public RteResource() {
		super();
		Connection con = null;
		try {
			con = this.getDS().getConnection();
			_dao = new RtemysqlDaoImpl(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainText() {
		return "Hello from rte resource";
	}

	/**
	 * Returns the rte if existing.
	 * 
	 * @response.representation.200.qname {http://www.gpxdb.com/gpxrs/api/v1/rte}rteid
	 * @response.representation.200.mediaType application/xml
	 * @response.representation.200.rte {@link http://www.topografix.com/GPX/1/1#rteType}
	 * 
	 * @return the requested rte.
	 */
	@GET
	@Path("{rteid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Rte getRteById(@PathParam("rteid") long rteid) {
		return _dao.getRteById(rteid);
	}

	// This method is called if HTML is request
	
	@GET
	@Path("{rteid}")
	@Produces(MediaType.TEXT_HTML)
	public String getRteByIdHtml(@PathParam("rteid") String rteid) {
		return "<html> " + "<title>" + "Hello Rte " + rteid + "</title>"
				+ "<body><h1>" + "Hello Rte:" + rteid + "</h1></body>"
				+ "</html> ";
	}

	@DELETE
	@Path("{rteid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void removeRtept(@PathParam("rteid") Long rteid) {
		_dao.deleteRte(rteid);
		return;
	}
	
}
