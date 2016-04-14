package com.gpxdb.resource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


public class GpxApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	
    public GpxApplication() {
    	System.out.println("GpxApplication constructor start.");
//        singletons.add(new AdminResource());
//    	System.out.println("added AdminResource");
        singletons.add(new ClientGpxResource());
    	System.out.println("added ClientGpxResource");
//        singletons.add(new DateConversionResource());
//        singletons.add(new GpxResource());
//        singletons.add(new GpxsResource());
//        singletons.add(new LovResource());
//        singletons.add(new RteptResource());
//        singletons.add(new RteResource());
//        singletons.add(new RtesResource());
    }
    @Override
    public Set<Class<?>> getClasses() {
    	return empty;
    }
    @Override
    public Set<Object> getSingletons() {
    	return singletons;
    }

}
