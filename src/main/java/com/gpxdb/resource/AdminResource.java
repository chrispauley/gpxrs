package com.gpxdb.resource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.gpxdb.model.*;
import com.gpxdb.dao.*;
import com.sun.jersey.api.view.Viewable;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PathParam;

@Path("/admin")
public class AdminResource {
	
    // TODO Write the interface for admin tasks
	@GET
	@Produces("text/html")
	@Path("/singup")
	public Viewable getSignUp() {
		return new Viewable("/public/signup", "Test");
	}

}
