package com.gpxdb.resource;

import org.gpxdb.model.*;

import com.gpxdb.dao.RteDao;
import com.gpxdb.dao.mysql.RtemysqlDaoImpl;
import com.gpxdb.model.lov.RelLink;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import java.lang.String;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/rtes")
public class RtesResource extends BaseResource {	
	RteDao _dao;
	
	public RtesResource() {
		super();
		Connection con = null;
		try {
			con = this.getDS().getConnection();
			_dao = new RtemysqlDaoImpl(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * @param uriInfo @Context UriInfo
	 * @param hsr HttpServletRequest
	 * @return Response
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayPlainTextHello(@Context UriInfo uriInfo, @Context HttpServletRequest hsr) {
		UriBuilder absolute = uriInfo.getAbsolutePathBuilder();
		Response.ResponseBuilder builder 
		= Response.ok("This is the GET text/plain response for \n" 
				+ absolute.clone().path("").build().toString()
				+"\nPlease try OPTIONS for wadl of this resource.");
		String scheme = hsr.getScheme();
		String serverName=hsr.getServerName();
		int serverPort = hsr.getServerPort();
		String contextPath = hsr.getContextPath();
		
		String url = scheme+"://"+serverName+":"+serverPort+contextPath;
		url += "/apidocs.html#rtes";
		builder.header("Link", new RelLink("help", url, "text/html"));
		return builder.build();
	}
	 
	// TODO Implement listRtesHtml with response builder.
//	@GET
//	@Produces(MediaType.TEXT_HTML)
//	public Response listRtes(
//			@QueryParam("start") @DefaultValue("1")  int start,
//		    @QueryParam("limit") @DefaultValue("10") int limit,
//		    @QueryParam("orderby") @DefaultValue("id") String orderby,
//		    @Context Response response,
//		    @Context UriInfo ui) {
//		return response;
//		return "<html> " + "<title>" + "Hello from rtes resource." + "</title>"
//				+ "<body><h1>" + "Hello from /rtes/list." + "</h1>" 
//				+ "<p>start=" + start + " limit=" + limit + " orderby=" + orderby + "</p>"
//				+ "</body></html>";
//	}

	// This method is called if XML is request
//	@GET
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	public ArrayList<Rte> listAll() {
//		return _dao.listAll();
//	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ArrayList<Rte> listAllPaged(
			@QueryParam("start") @DefaultValue("1")  int start,
		    @QueryParam("limit") @DefaultValue("10") int limit,
		    @QueryParam("orderby") @DefaultValue("id") String orderby) {
		if(orderby!=null){
			// TODO switch this for valid orderby clauses
		}
		return _dao.listAllPaged(start, limit, orderby);
	}	
	
}
