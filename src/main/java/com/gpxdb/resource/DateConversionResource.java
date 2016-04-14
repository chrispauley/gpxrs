package com.gpxdb.resource;

// TODO Remove this development code.

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gpxdb.resource.param.DateParam;

@Path("/dateutil")
@Produces(MediaType.TEXT_PLAIN)
public class DateConversionResource {
	@Path("/weekday/{date}")
	@GET
	public String getWeekday(@PathParam("date") DateParam dateParam) {
		return dateParam.getOriginalParam()
				+ " is on a "
				+ dateParam.getValue().dayOfWeek().getAsText()
				+ ".";
	}

}
