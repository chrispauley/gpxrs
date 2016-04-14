package com.gpxdb.resource.provider;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.gpxdb.model.*;

@Provider
@Produces("application/xml")
public class GpxResolver implements ContextResolver<JAXBContext> {

	private JAXBContext ctx;
	
	public GpxResolver(){
		try {
			this.ctx = JAXBContext.newInstance(Gpx.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	

	public JAXBContext getContext(Class<?> type) {
		if(type.equals(Gpx.class)){
			return ctx;
		} else {
			return null;
		}
	}

}
