package com.gpxdb.resource.exception;

import com.gpxdb.resource.exception.FaultInfo;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -339330063981442247L;
	
	final protected int responseStatusCode;
	
	protected FaultInfo faultInfo = null;

	public ServiceException(String message, int responseStatusCode) {
		super(message);
		this.responseStatusCode = responseStatusCode;
	}

	public ServiceException(String message, int responseStatusCode,
			FaultInfo faultInfo) {
		this(message, responseStatusCode);
		this.faultInfo = faultInfo;
	}

	public ServiceException(int responseStatusCode, FaultInfo faultInfo) {
		this(faultInfo.getReason(), responseStatusCode);
		this.faultInfo = faultInfo;
	}

	public int getResponseStatusCode() {
		return responseStatusCode;
	}

	public FaultInfo getFaultInfo() {
		return faultInfo;
	}
}
