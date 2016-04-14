package com.gpxdb.resource.param;

import javax.ws.rs.WebApplicationException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;


public class DateParam extends AbstractParam<DateTime> {
	private static final DateTimeFormatter ISO_BASIC = ISODateTimeFormat.basicDate();
	
	public DateParam(String param) throws WebApplicationException {
		super(param);
	}

	@Override
	protected DateTime parse(String param) throws Throwable {
		return ISO_BASIC.parseDateTime(param);
	}
}
