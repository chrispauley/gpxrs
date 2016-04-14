package com.gpxdb.resource;

import org.gpxdb.model.*;

import com.gpxdb.dao.WptDao;
import com.gpxdb.dao.mysql.WptMysqlDaoImpl;
import com.sun.jersey.api.view.Viewable;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import java.lang.String;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Path("/wpts")
public class WptsResource extends BaseResource {	
	WptDao _dao;
	
	public WptsResource() {
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
	public String sayHtmlHello() {
		return "Hello from wptsResource";
	}
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response listWpts(@Context UriInfo uriInfo,
			@QueryParam("start") @DefaultValue("1")  int start,
		    @QueryParam("limit") @DefaultValue("10") int limit,
		    @QueryParam("orderby") @DefaultValue("id") String orderby) {
		UriBuilder absolute = uriInfo.getAbsolutePathBuilder();
		String uripath = absolute.clone().path("").build().toString();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resource", "wpts");
        map.put("uri", uripath);
        map.put("issue","Choose xml or json representation.");
        
        return Response.ok().entity(new Viewable("/view/usage", map)).build(); 
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ArrayList<Wpt> listAllPaged(
			@QueryParam("start") @DefaultValue("1")  int start,
		    @QueryParam("limit") @DefaultValue("10") int limit,
		    @QueryParam("orderby") @DefaultValue("id") String orderby) {
		if(orderby!=null){
			// TODO switch this for valid orderby clauses
		}
//		System.out.println("start: " + start +"\nlimit: " + limit + "\norderby: " + orderby);
		return _dao.listAllPaged(start, limit, orderby);
	}
	

	
	
}
