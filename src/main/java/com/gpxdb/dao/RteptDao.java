/**
 * File: RteptDao.java
 */
package com.gpxdb.dao;

import java.util.ArrayList;

import org.gpxdb.model.*;
 

public interface RteptDao {
	
	/**
	 * @param Rtept
	 */
	public void updateRtept(Rtept rtept);
	
	/**
	 * @param rteid
	 */
	public void deleteRtept(long rptid);
	
	/**
	 * @param rteid
	 */
	public void deleteRteptLinks(long rptid);	
	
	/**
	 * @param rteid
	 */
	public void deleteRteptExtensions(long rptid);	
		
	/**
	 * @return long
	 */
	public long getTotalCount();

	/**
	 * @param rteid
	 * @return Rtept
	 */
	public Rtept findRteptById(long rptid);

	/**
	 * @param startindex
	 * @param limit
	 * @return ArrayList<Rtept>
	 */
	public ArrayList<Rtept> listAllRteptPaged(long startindex, long limit, String orderby);

	/**
	 * Insert.
	 *
	 * @param tsid the rteid
	 * @param rtept the rtept
	 * @return the rtept
	 */
	public long insert(long rteid, Rtept rtept);
}
