package com.gpxdb.resource.exception;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response.Status;


@Provider
public class CustomExceptionMapper implements ExceptionMapper<NotFoundException> {

	private Locale locale;
//	   private  ResourceBundle resource =   
//	        ResourceBundle.getBundle("com.gpxdb.resource.msg", locale.GERMAN);

    @Context
//    private HttpHeaders headers;

//    public Response toResponse(NotFoundException e) {
//    	System.out.println("e.status: " + e.getLocalizedMessage());
//    	System.out.println("getReason: " + e.getMessage());
//    	System.out.println("GpxId " + resource.getString("error.internal"));
//        return Response.status(Response.Status.NOT_FOUND).build();
//        return Response.status(e.getStatus()).
//        entity(new ErrorResponseConverter(e.getMessage(), e.getReason(), e.getErrorCode())).
//        type(headers.getMediaType()).
//        build();
//    }
    
    public Response toResponse(NotFoundException ex) {

    	//System.out.println("Gpxid " + resource.getString("error.gpx.notfound"));
        return Response.status(ex.getResponseStatusCode())
        .entity(ex.getFaultInfo()).type(MediaType.APPLICATION_XML).build();
    }
}