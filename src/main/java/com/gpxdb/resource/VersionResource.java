package com.gpxdb.resource;

import com.gpxdb.common.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/version")
public class VersionResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextVersion() {
		Version v = new Version();
		return "Hello from " + v.getVersion();
	}
	
	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlVersion() {
		Version v = new Version();
		return "<html> " + "<title>" + "Current Version is " + v.getVersion() + "</title>"
				+ "<body><h1>" + "Version Resource" + "</body></h1>"
				+ "<p>" + v.getVersion() + "</p>" + "</html> ";
	}
	
	// This method is called if XML is request
	@GET
	@Produces({ MediaType.TEXT_XML})
	public Version sayTextXMLVersion() {
//		//return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
		Version v = new Version();
		return v;
	}
	
	// This method is called if XML is request
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Version sayXMLVersion() {
		Version v = new Version();
		return v;
	}
	
	
	// This method is called if path parameter is present
	@Path("/{name}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHelloVersion(@PathParam("name") String name ) {
		Version v = new Version();
		return "<html> " + "<title>" + "Current Version is " + v.getVersion() + "</title>"
				+ "<body><h1>" + "Version Resource" + "</body></h1>"
				+ "<p>Path parameter: " + name + "</p>"
				+ "<p>" + v.getVersion() + "</p>" + "</html> ";
	}
	

}
