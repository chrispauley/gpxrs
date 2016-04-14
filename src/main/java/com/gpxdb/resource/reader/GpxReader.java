package com.gpxdb.resource.reader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;


import org.gpxdb.model.Gpx;
@Provider
@Consumes("text/xml")
public class GpxReader implements MessageBodyReader<Gpx>{

	
	public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		System.out.println("GpxReader.isReadable");
		return false;
	}

	
	public Gpx readFrom(Class<Gpx> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3, MultivaluedMap<String, String> arg4,
			InputStream arg5) throws IOException, WebApplicationException {
		System.out.println("GpxReader.readFrom");
		return null;
	}

}
