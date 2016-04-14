/**
 * File: TrkDao.java
 */
package com.gpxdb.dao;

import java.util.ArrayList;

import org.gpxdb.model.*;


// TODO: Auto-generated Javadoc
/**
 * The Interface TrksegDao.
 */
public interface TrksegDao {
	
	/**
	 * Update trkseg.
	 *
	 * @param trkseg the trkseg
	 */
	public void updateTrkseg(Trkseg trkseg);
	
	/**
	 * Delete trkseg.
	 *
	 * @param tsid the tsid
	 */
	public void deleteTrkseg(long tsid);
		
	/**
	 * Delete trkseg extensions.
	 *
	 * @param tsid the tsid
	 */
	public void deleteTrksegExtensions(long tsid);	
		
	/**
	 * Gets the total count.
	 *
	 * @return long
	 */
	public long getTotalCount();
	

	/**
	 * Find trkseg by id.
	 *
	 * @param tsid the tsid
	 * @return Trkseg
	 */
	public Trkseg findTrksegById(long tsid);

	/**
	 * List all trkseg paged.
	 *
	 * @param startindex the startindex
	 * @param limit the limit
	 * @param orderby the list order
	 * @return ArrayList<Trkseg>
	 */
	public ArrayList<Trkseg> listAllTrksegPaged(long startindex, long limit, String orderby);

	/**
	 * Insert.
	 *
	 * @param trkid the trkid
	 * @param trkseg the trkseg
	 * @return the long
	 */
	public long insert(long trkid, Trkseg trkseg);
}
