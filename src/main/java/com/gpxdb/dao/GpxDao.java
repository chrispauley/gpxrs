package com.gpxdb.dao;
/**
 * Data Access Object interface for Gpx data.
 *
 * @author  Chris Pauley
 * @version ${version}
 */

import org.gpxdb.model.*;

import java.util.ArrayList;


/**
 * The Interface GpxDao.
 */
public interface GpxDao  {
	
	/**
	 * Delete.
	 *
	 * @param gpxid the gpxid
	 */
	public void delete(long gpxid);
	
	/**
	 * @param gpxid
	 * @return
	 */
	public Gpx findById(long gpxid);

	/**
	 * Gets the total count.
	 *
	 * @return the total count
	 */
	public long getTotalCount();
	
	/**
	 * Update.
	 *
	 * @param gpx the gpx to update.
	 * @return the long
	 */
	public long insert(Gpx gpx);
	

	/**
	 * List all.
	 *
	 * @return the array list
	 */
	public ArrayList<Gpx> listAll();
	
	

	/**
	 * @param creator of the gpx
	 * @param startindex
	 * @param limit
	 * @return
	 */
	public ArrayList<Gpx> listAllByCreator(String creator, long startindex, long limit);
	
	/**
	 * List all paged.
	 *
	 * @param startindex the startindex
	 * @param limit the limit
	 * @return the array list
	 */
	public ArrayList<Gpx> listAllPaged(long startindex, long limit, String orderby);
	
	/**
	 * List by gpx name.
	 *
	 * @param name the name
	 * @return the array list of gpx
	 */
	public ArrayList<Gpx>  listByGpxName(String name);
	

	/**
	 * Update a gpx. This replaces the gpx
	 * in the persistence layer.
	 *
	 * @param gpx the gpx
	 * @return the long gpxid
	 */
	public long update(Gpx gpx);


}
