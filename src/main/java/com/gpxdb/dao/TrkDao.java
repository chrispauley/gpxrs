/**
 * File: TrkDao.java
 */
package com.gpxdb.dao;

import java.util.ArrayList;

import org.gpxdb.model.*;

/**
 * @author chris
 *
 */
public interface TrkDao {
	
	/**
	 * Adds the trk.
	 *
	 * @param gpxid the gpxid
	 * @param trk the trk
	 * @return the trk
	 */
	public Trk addTrk(long gpxid, Trk trk);
	
	/**
	 * @param gpx
	 * @return Gpx
	 */
	public Gpx getTrksForGpx(Gpx gpx);
	
	/**
	 * @param gpx
	 */
	public void addTrks(Gpx gpx);
	
	/**
	 * @param gpx
	 */
	public void updateTrk(Trk trk);
	
	/**
	 * @param gpxid
	 */
	public void deleteTrks(long gpxid);
	
	/**
	 * @param trkid
	 */
	public void deleteTrk(long trkid);
	
	/**
	 * @param trkid
	 * @return Trk Does not include List<trkseg>
	 */
	public Trk findTrkById(long trkid);
	
	/**
	 * @param trkid
	 * @return Trk
	 */
	public Trk getTrkById(long trkid);
	
	/**
	 * @return ArrayList<Trk>
	 */
	public ArrayList<Trk> listAll();
	
	/**
	 * @param startindex
	 * @param limit
	 * @return
	 */
	public ArrayList<Trk> listAllPaged(long startindex, long limit, String orderby);
	
	/**
	 * @return long
	 */
	public long getTotalCount();

}
