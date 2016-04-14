package com.gpxdb.resource;

import java.math.BigDecimal;
import java.util.Date;

import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PathParam;

@Path("/my")
public class ClientGpxResource extends BaseResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayTextPlainMy() {
		return "Hello from my gpx resource.";
	}

	@GET
	@Produces({ MediaType.TEXT_XML })
	public String sayTextXMLMy() {
		return "<?xml version=\"1.0\"?>"
				+ "<hello> Hello from my gpx resource." + "</hello>";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMy() {
		return "<html> " + "<title>" + "Hello from my gpx resource."
				+ "</title>" + "<body><h1>" + "Hello from my gpx resource."
				+ "</h1>" + "</body></html>";
	}

	// rest/gpxs?start=0&limit=10&orderby=id
	@Path("/gpxs")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyGpxs(
			@QueryParam("start") @DefaultValue("0") int start,
			@QueryParam("limit") @DefaultValue("10") int limit,
			@QueryParam("orderby") @DefaultValue("id") String orderby) {
		return "<html> " + "<title>" + "Hello from /my/gpxs resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/gpxs." + "</h1>"
				+ "<p>start=" + start + " limit=" + limit + "</body></html>";
	}

	@Path("/rtes")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyRtes(
			@QueryParam("start") @DefaultValue("0") int start,
			@QueryParam("limit") @DefaultValue("10") int limit) {
		return "<html> " + "<title>" + "Hello from /my/rtes resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/rtes." + "</h1>"
				+ "<p>start=" + start + " limit=" + limit + "</body></html>";
	}

	@Path("/rtepts")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyRtepts(
			@QueryParam("start") @DefaultValue("0") int start,
			@QueryParam("limit") @DefaultValue("10") int limit) {
		return "<html> " + "<title>" + "Hello from /my/rtepts resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/rtepts."
				+ "</h1>" + "<p>start=" + start + " limit=" + limit
				+ "</body></html>";
	}

	@Path("/trks")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyTrks(
			@QueryParam("start") @DefaultValue("0") int start,
			@QueryParam("limit") @DefaultValue("10") int limit) {
		return "<html> " + "<title>" + "Hello from /my/trks resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/trks." + "</h1>"
				+ "<p>start=" + start + " limit=" + limit + "</body></html>";
	}

	@Path("/trksegs")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyTrksegs(
			@QueryParam("start") @DefaultValue("0") int start,
			@QueryParam("limit") @DefaultValue("10") int limit) {
		return "<html> " + "<title>" + "Hello from /my/trksegs resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/trksegs."
				+ "</h1>" + "<p>start=" + start + " limit=" + limit
				+ "</body></html>";
	}

	@Path("/trkpts")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyTrkpts(
			@QueryParam("start") @DefaultValue("0") int start,
			@QueryParam("limit") @DefaultValue("10") int limit) {
		return "<html> " + "<title>" + "Hello from /my/trkpts resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/trkpts."
				+ "</h1>" + "<p>start=" + start + " limit=" + limit
				+ "</body></html>";
	}

	@Path("/wpts")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyWpts(
			@QueryParam("start") @DefaultValue("0") int start,
			@QueryParam("limit") @DefaultValue("10") int limit) {
		return "<html> " + "<title>" + "Hello from /my/wpts resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/wpts." + "</h1>"
				+ "<p>start=" + start + " limit=" + limit
				+ "</p></body></html>";
	}

	/*****************/
	@Path("/gpx/{gpxid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyGpx(@PathParam("gpxid") long gpxid) {
		return "<html> " + "<title>" + "Hello from /my/gpx resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/gpx." + "</h1>"
				+ "<p>gpxid=" + gpxid + "</p></body></html>";
	}

	@Path("/rte/{rteid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyRte(@PathParam("rteid") long rteid) {
		return "<html> " + "<title>" + "Hello from /my/rte resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/rte." + "</h1>"
				+ "<p>rteid=" + rteid + "</p></body></html>";
	}

	@Path("/rtept/{rptid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyRtept(@PathParam("rptid") long rptid) {
		return "<html> " + "<title>" + "Hello from /my/rtept resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/rtept." + "</h1>"
				+ "<p>rptid=" + rptid + "</p></body></html>";
	}

	@Path("/trk/{trkid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyTrk(@PathParam("trkid") long trkid) {
		return "<html> " + "<title>" + "Hello from /my/trk resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/trk." + "</h1>"
				+ "<p>trkid=" + trkid + "</p></body></html>";
	}

	@Path("/trkseg/{id}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyTrkseg(@PathParam("id") long id) {
		return "<html> " + "<title>" + "Hello from /my/trkseg resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/trkseg."
				+ "</h1>" + "<p>id=" + id + "</p></body></html>";
	}

	@Path("/trkpt/{tptid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyTrkpt(@PathParam("tptid") long tptid) {
		return "<html> " + "<title>" + "Hello from /my/trkpt resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/trkpt." + "</h1>"
				+ "<p>tptid=" + tptid + "</p></body></html>";
	}

	@Path("/wpt/{wptid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyWpt(@PathParam("wptid") long wptid) {
		return "<html> " + "<title>" + "Hello from /my/wpt resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/wpt." + "</h1>"
				+ "<p>wptid=" + wptid + "</p></body></html>";
	}

	/*****************/
	@Path("/gpx/{gpxid}/metadata")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyGpxMetadata(@PathParam("gpxid") long gpxid) {
		return "<html> " + "<title>"
				+ "Hello from /my/gpx/{gpxid}/metadata resource." + "</title>"
				+ "<body><h1>" + "Hello from /my/gpx/" + gpxid
				+ "/metadata </h1>" + "</body></html>";
	}

	@Path("/gpx/{gpxid}/metadata/author")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyGpxAuthor(@PathParam("gpxid") long gpxid) {
		return "<html> " + "<title>"
				+ "Hello from /my/gpx/{gpxid}/metadata/author resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/gpx/" + gpxid
				+ "/metadata/author </h1>" + "</body></html>";
	}

	@Path("/gpx/{gpxid}/metadata/bounds")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyGpxBounds(@PathParam("gpxid") long gpxid,
			@QueryParam("minlat") @DefaultValue("0") BigDecimal minlat,
			@QueryParam("minlon") @DefaultValue("0") BigDecimal minlon,
			@QueryParam("maxlat") @DefaultValue("0") BigDecimal maxlat,
			@QueryParam("maxlon") @DefaultValue("0") BigDecimal maxlon) {
		return "<html> " + "<title>"
				+ "Hello from /my/gpx/{gpxid}/metadata/bounds resource."
				+ "</title>" + "<body><h1>" + "Hello from  /my/gpx/" + gpxid
				+ "/metadata/bounds?" + "minlat=" + minlat + "&" + "minlon="
				+ minlon + "&" + "maxlat=" + maxlat + "&" + "maxlon=" + maxlon
				+ "</h1>" + "</body></html>";
	}

	@Path("/gpx/{gpxid}/metadata/extensions")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyGpxExtensions(@PathParam("gpxid") long gpxid) {
		return "<html> " + "<title>"
				+ "Hello from /my/gpx/{gpxid}/metadata/extensions resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/gpx/" + gpxid
				+ "/metadata/extensions </h1>" + "</body></html>";
	}

	@Path("/gpx/{gpxid}/metadata/keywords")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyGpxKeywords(@PathParam("gpxid") long gpxid) {
		return "<html> " + "<title>"
				+ "Hello from /my/gpx/{gpxid}/metadata/keywords resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/gpx/" + gpxid
				+ "/metadata/keywords </h1>" + "</body></html>";
	}

	@Path("/gpx/{gpxid}/metadata/links")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyGpxLinks(@PathParam("gpxid") long gpxid) {
		return "<html> " + "<title>"
				+ "Hello from /my/gpx/{gpxid}/metadata/links resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/gpx/" + gpxid
				+ "/metadata/links </h1>" + "</body></html>";
	}

	@Path("/gpx/{gpxid}/extensions/{extid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyGpxExtensions(@PathParam("gpxid") long gpxid,
			@PathParam("extid") long extid) {
		return "<html> " + "<title>"
				+ "Hello from /my/gpx/{gpxid}/extensions/{extid} resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/gpx/" + gpxid
				+ "/extensions/" + extid + "</h1>" + "</body></html>";
	}

	@Path("/gpx/{gpxid}/wpt/{wptid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyGpxWpt(@PathParam("gpxid") long gpxid,
			@PathParam("wptid") long wptid) {
		return "<html> " + "<title>"
				+ "Hello from /my/gpx/{gpxid}/wpt/{wptid} resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/gpx/" + gpxid
				+ "/wpt/" + wptid + "</h1>" + "</body></html>";
	}

	@Path("/gpx/{gpxid}/rte/{rteid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyGpxRte(@PathParam("gpxid") long gpxid,
			@PathParam("rteid") long rteid) {
		return "<html> " + "<title>"
				+ "Hello from /my/gpx/{gpxid}/rte/{rteid} resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/gpx/" + gpxid
				+ "/rte/" + rteid + "</h1>" + "</body></html>";
	}

	@Path("/gpx/{gpxid}/trk/{trkid: [0-9]+}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayTextHtmlMyGpxTrk(@PathParam("gpxid") long gpxid,
			@PathParam("trkid") long trkid) {
		return "<html> " + "<title>"
				+ "Hello from /my/gpx/{gpxid}/trk/{trkid} resource."
				+ "</title>" + "<body><h1>" + "Hello from /my/gpx/" + gpxid
				+ "/trk/" + trkid + "</h1>" + "</body></html>";
	}

	@Path("/responsebuilder")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response sayTextHtmlMyGpxResponseObject() {
		ResponseBuilderImpl builder = new ResponseBuilderImpl();
		builder.status(200);
		Date expirationDate = new Date(System.currentTimeMillis() + 3000); // 3
																			// secs
		builder.expires(expirationDate);
		Response r = builder.build();
		return r;
	}

}
