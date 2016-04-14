package com.gpxdb.resource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gpxdb.model.*;
import com.gpxdb.dao.*;
import com.gpxdb.dao.mysql.*;
import com.gpxdb.model.lov.RelLink;
import com.gpxdb.resource.exception.FaultInfo;
import com.gpxdb.resource.exception.NotFoundException;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.Consumes;
import javax.xml.bind.JAXBElement;

import com.sun.jersey.api.view.Viewable;


@Path("/gpx")
public class GpxResource extends BaseResource {	
	
	GpxDao _dao;
	
	public GpxResource() {
		super();
		Connection con = null;
		try {
			con = this.getDS().getConnection();
			_dao = new GpxmysqlDaoImpl(con);
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
				+"\nPlease try OPTIONS for a web application description of this resource.");
		String scheme = hsr.getScheme();
		String serverName=hsr.getServerName();
		int serverPort = hsr.getServerPort();
		String contextPath = hsr.getContextPath();
		String url = scheme+"://"+serverName+":"+serverPort+contextPath;
		url += "/apidocs.jsp#gpx";
		builder.header("Link", new RelLink("help", url, null));
		return builder.build();
	}

	// This method is called if XML is request
	@GET
	@Path("{gpxid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response sayGpxById(@PathParam("gpxid") long gpxid,
			@Context UriInfo uriInfo) {
		 Gpx gpx = this.getGpxById(gpxid);
		 if(null==gpx){	 
			 FaultInfo fault = new FaultInfo();
			 fault.setException("");
			 fault.setErrorCode(404);
			 fault.setReason("Couldn't find the gpx in the database.");
			 fault.setResource("GpxResource");
			 throw new NotFoundException("Gpx: " + gpxid +  " not found.", fault);
		}
		 Response.ResponseBuilder responseBuilder = Response.ok(gpx);
		 this.addHeaderLink(uriInfo, responseBuilder, null);
		return responseBuilder.build();
	}
	

	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response gpxHTML(@Context UriInfo uriInfo,
			@Context HttpServletResponse servletResponse) {
		System.out.println("GpxResource: missing gpxid");
		UriBuilder absolute = uriInfo.getAbsolutePathBuilder();
		String uripath = absolute.clone().path("").build().toString();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resource", "gpx");
        map.put("uri", uripath+"/2");
        map.put("issue","Request missing gpxid.");
        
        java.util.Map<String,Object[]> pmap = new java.util.LinkedHashMap<String, Object[]>(); 
        Object[] a0 = {"gpxid","int","Internal id for gpx."};
        pmap.put("ao", a0);
        map.put("paramArray", pmap);
        return Response.ok().entity(new Viewable("/view/usage", map)).build(); 
	}
	
	// TODO Remove this development code.
	/***
	 * Test code for jersey mvc
	 *   /web-inf/jsp/cart.js
	 *   web.xml - filter
	 *   
	 * @return
	 */
	@Path("gpxcart")
	@GET
    @Produces("text/html")
    public Response cart() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", "chris");
        List<String> l = new ArrayList<String>();
        l.add("light saber");
        l.add("fremen clothes");
        map.put("items", l);
        return Response.ok().entity(new Viewable("/view/cart", map)).build(); 
    }
	
	@POST 
	@Consumes("application/x-www-form-urlencoded")
    public Response postUser(MultivaluedMap<String, String> formParams,
    		@Context SecurityContext securityContext) {
		ResponseBuilderImpl builder = new ResponseBuilderImpl();

		builder.status(200);
		Date expirationDate =  new Date(System.currentTimeMillis() + 3000); // 3 secs
        builder.expires(expirationDate);
		Response r = builder.build();
		return r;
	}
	
	@POST
	@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML})
	@Produces({MediaType.TEXT_PLAIN})
	public Response createGpx(JAXBElement<Gpx> jaxb_gpx,
			@Context UriInfo uriInfo){
		ResponseBuilderImpl builder = new ResponseBuilderImpl();
		Gpx g = (Gpx) jaxb_gpx.getValue();
		long resultid = _dao.insert(g);
		builder.status(Status.CREATED);
		builder.type(MediaType.TEXT_PLAIN);
		builder.entity("Created: " + resultid);
		
		
		UriBuilder absolute = uriInfo.getAbsolutePathBuilder();
		String strUrl = absolute.clone().path(Long.toString(resultid)).build().toString();
		RelLink viewLink = new RelLink("view", strUrl,"application/xml");
		builder.header("Link", viewLink);
		Response r = builder.build();
		return r;
	}
	
	private Gpx getGpxById(long gpxid){
		Gpx gpx = null;
		Connection con = null;
		try {
			con = this.getDS().getConnection();
			_dao = new GpxmysqlDaoImpl(con);
			gpx = _dao.findById(gpxid);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return gpx;
	}
	
	
	
	// TODO remove this from production
	/**
	 * Development code. 
	 * @param uriInfo
	 * @param builder
	 * @param link
	 */
	private void addHeaderLink(UriInfo uriInfo, Response.ResponseBuilder builder, RelLink link){
		UriBuilder absolute = uriInfo.getAbsolutePathBuilder();
		String strUrl = absolute.clone().path("cancel").build().toString();
		builder.header("Link", new RelLink("cancel", strUrl, null));
	}
	
}
