package com.gpxdb.common;

/**
 * The Class AppAbout.
 * 
 * @author Chris Pauley
 * 
 */
public class AppAbout {

	private final String DEFAULT_SHORT_VERSION = 
		"GPXDB is database engine for storing Gpx data";
	private final String DEFAULT_DESCRIPTION =
		"GPXDB enables web site builders to utilize location based data to "
		+ "store points of interest (Waypoints), interactive tours "
		+ "(routes with hyperlinks), bike and hike routes and tracks "
		+ "in a database. ";
	
	private String shortVersion;
	private String description;

	public AppAbout() {
		this.shortVersion = this.DEFAULT_SHORT_VERSION;
		this.description = this.DEFAULT_DESCRIPTION;
	}

	public AppAbout(String shortVersion, String description) {
		super();
		this.shortVersion = shortVersion;
		this.description = description;
	}

	public String shortDescription() {
		return this.shortVersion;
	}
	public String description(){
		return this.description;
	}

}
