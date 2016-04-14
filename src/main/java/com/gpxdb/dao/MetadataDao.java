/**
 * File: MetadataDao.java
 */
package com.gpxdb.dao;

import java.sql.SQLException;

import org.gpxdb.model.*;

// TODO: Auto-generated Javadoc
/**
 * The Interface MetadataDao.
 */
public interface MetadataDao {
	
	/**
	 * Gets the metadata for a Gpx.
	 *
	 * @param gpx the gpx
	 * @return the metadata
	 */
	public void getMetadata(Gpx gpx);
	
	/**
	 * Adds the metadata.
	 *
	 * @param gpx the gpx
	 */
	public void addMetadata(Gpx gpx);
	

	/**
	 * Update metadata for a specific Metadata object.
	 * 
	 * @param metid the metid
	 * @param metadata the metadata
	 */
	public void updateMetadata(long metid, Metadata metadata);

	/**
	 * Update metadata for a specific Metadata object.
	 * 
	 * @param metid the metid
	 * @param metadata the metadata
	 */
	public void updateMetadata(Metadata metadata);
	
	/**
	 * Delete metadata.
	 *
	 * @param metid the metid
	 * @throws SQLException the sQL exception
	 */
	public void deleteMetadata(long metid);

	/**
	 * Find by gpxid.
	 *
	 * @param gpxid the gpxid
	 * @return the metadata
	 */
	public Metadata findByGpxid(long gpxid);
}
