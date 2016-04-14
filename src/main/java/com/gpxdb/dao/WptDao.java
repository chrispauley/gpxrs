/**
 * File: WptDao.java
 */
package com.gpxdb.dao;

import java.util.ArrayList;

import org.gpxdb.model.*;

// TODO: Auto-generated Javadoc
/**
 * The Interface WptDao.
 *
 * @author chris
 */
public interface WptDao {
	
	/**
	 * Adds the wpts.
	 *
	 * @param gpx the gpx
	 */
	public void addWpts(Gpx gpx);
	

	/**
	 * Insert waypoint.
	 *
	 * @param gpxid the gpxid
	 * @param wpt the wpt
	 * @return the long
	 */
	public long insertWaypoint(long gpxid, Wpt wpt);
	
	/**
	 * Delete wpt.
	 *
	 * @param wptid the wptid
	 */
	public void deleteWpt(long wptid);
	
	/**
	 * Delete wpts.
	 *
	 * @param gpxid the gpxid
	 */
	public void deleteWpts(long gpxid);
	
	/**
	 * Delete wpt links.
	 *
	 * @param wptid the wptid
	 */
	public void deleteWptLinks(long wptid);
	
	/**
	 * Find all.
	 *
	 * @return ArrayList<Wpt>
	 */
	public ArrayList<Wpt> findAll();
	
	/**
	 * Find by wpt id.
	 *
	 * @param wptid the wptid
	 * @return Wpt
	 */	
	public Wpt findByWptId(long wptid);
	
	/**
	 * Fetches total number of Wpt.
	 *
	 * @return long
	 */	
	public long getTotalCount();
	
	
	/**
	 * Gets the wpts.
	 *
	 * @param gpx the gpx
	 * @return the wpts
	 */
	public Gpx getWpts(Gpx gpx);
	
	/**
	 * List all paged.
	 *
	 * @param startindex the startindex
	 * @param limit the limit
	 * @return ArrayList<Wpt>
	 */
	public ArrayList<Wpt> listAllPaged(long startindex, long limit, String orderby);
	
	
	/**
	 * Update wpt.
	 *
	 * @param wpt the wpt
	 * @return the wpt
	 */
	public Wpt updateWpt(Wpt wpt);
	
	
	/**
	 * Update wpts.
	 *
	 * @param gpx the gpx
	 */
	public void updateWpts(Gpx gpx);


	/**
	 * Find by wpts by location in decimal format: lat,lon.
	 *
	 * @param location the location
	 * @return the array list
	 */
	public ArrayList<Wpt> findByWptsByLoc(String location);
}
