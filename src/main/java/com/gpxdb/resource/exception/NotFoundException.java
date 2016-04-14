package com.gpxdb.resource.exception;

import java.net.HttpURLConnection;

public class NotFoundException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public static final int RESPONSE_STATUS_CODE = HttpURLConnection.HTTP_NOT_FOUND;

	public NotFoundException(String message) {
		super(message, RESPONSE_STATUS_CODE);
	}

	public NotFoundException(String message, FaultInfo faultInfo) {
		super(message, RESPONSE_STATUS_CODE, faultInfo);
	}

	public NotFoundException(FaultInfo faultInfo) {
		super(RESPONSE_STATUS_CODE, faultInfo);
	}
}
