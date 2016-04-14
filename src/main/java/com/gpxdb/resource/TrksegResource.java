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

@Path("/trkseg")
public class TrksegResource extends BaseResource {
	protected TrksegDao _dao;
	public TrksegResource() {
		super();
		Connection con = null;
		try {
			con = this.getDS().getConnection();
			_dao = new TrksegMysqlDaoImpl(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainText() {
		return "Hello from trkseg resource";
	}

	/**
	 * Returns the track segment if existing.
	 * 
	 * @response.representation.200.qname {http://www.gpxdb.com/gpxrs/api/v1/trkseg}tsid
	 * @response.representation.200.mediaType application/xml
	 * @response.representation.200.trkseg {@link http://www.topografix.com/GPX/1/1#trksegType}
	 * 
	 * @return the requested trkseg.
	 */
	@GET
	@Path("{tsid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Trkseg getById(@PathParam("tsid") long tsid) {
		return _dao.findTrksegById(tsid);
	}

	// This method is called if HTML is request
	
	@GET
	@Path("{tsid}")
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello(@PathParam("wptid") String tsid) {
		return "<html> " + "<title>" + "Hello Trkseg " + tsid + "</title>"
				+ "<body><h1>" + "Hello Trkseg:" + tsid + "</h1></body>"
				+ "</html> ";
	}

	@DELETE
	@Path("{tsid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(@PathParam("tsid") Long tsid) {
		_dao.deleteTrkseg(tsid);
		return;
	}
	
//	@PUT
//	@Path("{tsid}")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public void add(@PathParam("tsid") Long tsid) {
//		_dao.insert(trkid, trkseg)
//		return;
//	}	

}
