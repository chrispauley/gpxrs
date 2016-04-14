/**
 * File: TrkDao.java
 */
package com.gpxdb.dao;

import java.util.ArrayList;

import org.gpxdb.model.*;


public interface TrkptDao {
	
	/**
	 * @param Trkpt
	 */
	public void updateTrkpt(Trkpt trkpt);
	
	/**
	 * @param tptid
	 */
	public void deleteTrkpt(long tptid);
	
	/**
	 * @param tptid
	 */
	public void deleteTrkptLinks(long tptid);	
	
	/**
	 * @param tptid
	 */
	public void deleteTrkptExtensions(long tptid);	
		
	/**
	 * @return long
	 */
	public long getTotalCount();

	/**
	 * @param tptid
	 * @return Trkpt
	 */
	public Trkpt findTrkptById(long tptid);
	
	/**
	 * @param tsid
	 * @return ArrayList<Trkpt>
	 */
	public ArrayList<Trkpt> getTrkptsByTrksegId(long tsid);

	/**
	 * @param startindex
	 * @param limit
	 * @return ArrayList<Trkpt>
	 */
	public ArrayList<Trkpt> listAllTrkptPaged(long startindex, long limit, String orderby);

	/**
	 * Insert.
	 *
	 * @param tsid the tsid
	 * @param trkpt the trkpt
	 * @return the trkpt
	 */
	public long insert(long tsid, Trkpt trkpt);
}
