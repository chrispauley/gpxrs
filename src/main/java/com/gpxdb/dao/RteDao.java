/**
 * File: RteDao.java
 */
package com.gpxdb.dao;

import java.util.ArrayList;

import org.gpxdb.model.*;

// TODO: Auto-generated Javadoc
/**
 * The Interface RteDao.
 *
 * @author chris
 */
public interface RteDao {
	
	/**
	 * Adds the rtes.
	 *
	 * @param gpx the gpx
	 */
	public void addRtes(Gpx gpx);
	
	/**
	 * Delete rte.
	 *
	 * @param retid the retid
	 */
	public void deleteRte(long rteid);
	
	/**
	 * Delete rtes.
	 *
	 * @param gpxid the gpxid
	 */
	public void deleteRtes(long gpxid);
	
	/**
	 * Gets the rte by id.
	 *
	 * @param rteid the rteid
	 * @return Rte
	 */
	public Rte getRteById(long rteid);
	
	/**
	 * Gets the rtept by id.
	 *
	 * @param rptid the rptid
	 * @return Rtept
	 */
	public Rtept getRteptById(long rptid);
	
	/**
	 * Gets the rtes.
	 *
	 * @param gpx the gpx
	 * @return the rtes
	 */
	public Gpx getRtes(Gpx gpx);
	
	/**
	 * Gets the total count.
	 *
	 * @return long
	 */
	public long getTotalCount();
	

	/**
	 * Insert gpx route.
	 *
	 * @param gpxid the gpxid
	 * @param rte the rte
	 * @return the long
	 */
	public long insertGpxRoute(long gpxid, Rte rte);
	
	
	/**
	 * List all.
	 *
	 * @return ArrayList<Rte>
	 */
	public ArrayList<Rte> listAll();
	
	/**
	 * List all paged.
	 *
	 * @param startindex the startindex
	 * @param limit the limit
	 * @return ArrayList<Rte>
	 */
	public ArrayList<Rte> listAllPaged(long startindex, long limit, String orderby);

}
