package com.gpxdb.resource;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.gpxdb.model.*;
import com.gpxdb.model.lov.*;
import com.gpxdb.dao.GpxDao;
import com.gpxdb.dao.mysql.GpxmysqlDaoImpl;
import com.sun.jersey.api.view.Viewable;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

import org.joda.time.DateTime;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import java.io.IOException;
import java.lang.String;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Path("/gpxs")
public class GpxsResource extends BaseResource {
	GpxDao _dao;
	@Context private HttpServletRequest request;
	
	public GpxsResource() {
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
	public Response sayPlainText(@Context UriInfo uriInfo, @Context HttpServletRequest hsr) {
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
		url += "/apidocs.html#gpxs";
		builder.header("Link", new RelLink("help", url, "text/html"));
		return builder.build();
	}
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response listGpxs(@Context UriInfo uriInfo,
			@QueryParam("start") @DefaultValue("1")  int start,
		    @QueryParam("limit") @DefaultValue("10") int limit,
		    @QueryParam("orderby") @DefaultValue("id") String orderby) {
		UriBuilder absolute = uriInfo.getAbsolutePathBuilder();
		String uripath = absolute.clone().path("").build().toString();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resource", "gpxs");
        map.put("uri", uripath);
        map.put("issue","Choose xml or json representation.");
        
        JSONObject json = createJsonObject();
        map.put("json", json);
        
        return Response.ok().entity(new Viewable("/view/usage", map)).build(); 
	}


	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ArrayList<Gpx> listAllPaged(
			@QueryParam("start") @DefaultValue("1")  int start,
		    @QueryParam("limit") @DefaultValue("10") int limit,
		    @QueryParam("orderby") @DefaultValue("id") String orderby,
		    @Context UriInfo uriInfo) {
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.queryParam("start", "{start}");
		builder.queryParam("limit", "{limit}");
		builder.queryParam("orderby", "{orderby}");
		if(orderby!=null){
			// TODO switch this for valid orderby clauses
		}
		System.out.println("GpxsResource.listAllPaged: start="+start+";limit="+limit+"orderby="+orderby);
		return this.getListPaged(start, limit, orderby);
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_ATOM_XML})
	public Feed atomList(
			@QueryParam("start") @DefaultValue("1")  int start,
		    @QueryParam("limit") @DefaultValue("10") int limit,
		    @QueryParam("orderby") @DefaultValue("id") String orderby,
		    @Context UriInfo uriInfo,  @Context HttpServletRequest hsr) {
		System.out.println("get: application/atom+xml");
		if(orderby!=null){
			// TODO switch this for valid orderby clauses
		}
		ArrayList<Gpx> gpxs =_dao.listAllPaged(start, limit, orderby);
		long totalCount = _dao.getTotalCount();
		
		String scheme = hsr.getScheme();
		String serverName=hsr.getServerName();
		int serverPort = hsr.getServerPort();
		String contextPath = hsr.getContextPath();
//		String servletPath = hsr.getServletPath();
		String url = scheme+"://"+serverName+":"+serverPort+contextPath;
		
		Response.ResponseBuilder responseBuilder = Response.ok();
		this.addHeaderLink(uriInfo, "list", "text/xml", responseBuilder);
		
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();

		Abdera abdera = new Abdera();
		Feed feed = abdera.newFeed();
		DateTime now = new DateTime();
		String year = Long.toString(now.getYear());
		feed.setId("tag:gpxdb.com," + year + ":gpxs");
		feed.setTitle("GPXDB List of gpx");
		feed.setSubtitle("This lists the gpx items represented in the gpxdb database. "
				+ "Includes " + gpxs.size() + " of " + totalCount + " items.");
		feed.setUpdated(new Date());
		feed.addAuthor("Chris Pauley");
		feed.addCategory("scheme", "term", "label");
		// @TODO add links for self, next and previous
		//
		String baseUrl = builder.clone().path("").build().toString();
		feed.addLink(baseUrl,"self");
		if(totalCount> (start*limit)){
			feed.addLink(baseUrl+"?start=" + (start+1)
					+ "&limit="+limit,"next","application/xml","GPXDB List of gpx","en",-1 );
		}
		Entry entry;
		String entryId ="";
		
		// @TODO Map gpx metadata to the atom feed.
		for(Gpx g : gpxs){
			entry = feed.addEntry();
			entryId = "tag:gpxdb.com," + year + ":/gpx/" + g.getValueObjectId();
			entry.setId(entryId);
			entry.setTitle(g.getMetadata().getName());
			entry.setSummaryAsHtml("<p>This is the entry title</p>");
			entry.setUpdated(new Date());
			entry.setPublished(new Date());
			entry.addLink(url + "/api/v1/gpx/" + g.getValueObjectId(),
					"item","application/xml","","",-1 );
		}

		return feed;
	}
	
	@GET
	@Path("count")
	@Produces({MediaType.TEXT_PLAIN})
	public Response getTotalCount(){
		ResponseBuilderImpl builder = new ResponseBuilderImpl();
		long count = _dao.getTotalCount();
		builder.status(Status.OK).entity(Long.toString(count));
		Response response = builder.build();
		return response;
	}
	
	
	private ArrayList<Gpx> getListPaged(long startindex, long limit, String orderby) {
		ArrayList<Gpx> gpxs;
		gpxs = this._dao.listAllPaged(startindex, limit, orderby);
		return gpxs;
	}
	
//	private void addHeaderLink(UriInfo uriInfo, Response.ResponseBuilder builder){
//		UriBuilder absolute = uriInfo.getAbsolutePathBuilder();
//		String strUrl = absolute.clone().path("cancel").build().toString();
//		builder.header("Link", new RelLink("cancel", strUrl, null));
//	}
	
	private void addHeaderLink(UriInfo uriInfo, String rel, String type, Response.ResponseBuilder builder){
		UriBuilder absolute = uriInfo.getAbsolutePathBuilder();
		String strUrl = absolute.clone().build().toString();
		builder.header("Link", new RelLink(rel, strUrl, type));
	}
	
	private JSONObject createJsonObject(){
		JSONObject json = new JSONObject();
	    JSONArray toplevel = new JSONArray();
	    JSONObject sublevel;

	    try{

	        json.put("id", "node" + 0);
	        json.put("name", "name" + 0);


            sublevel = new JSONObject();
            sublevel.put("paramName", "start");
            sublevel.put("type", "int");
            sublevel.put("description", "Default is 1.");
            sublevel = new JSONObject();
            sublevel.put("paramName", "limit");
            sublevel.put("type", "int");
            sublevel.put("description", "Default is 10.");
            toplevel.put(sublevel);
            sublevel = new JSONObject();
            sublevel.put("paramName", "orderby");
            sublevel.put("type", "String");
            sublevel.put("description", "Default is by id.");
            toplevel.put(sublevel);

	        json.put("parameters", toplevel);
	    } catch (JSONException jse) {

	    }
	    return json;
	}
	
}
