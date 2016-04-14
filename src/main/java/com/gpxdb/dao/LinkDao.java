package com.gpxdb.dao;

import java.util.ArrayList;

import org.gpxdb.model.Link;

public interface LinkDao {

	/**
	 * Add a Link object to a Metadata object by the metadata id.
	 * @param metid
	 * @param link
	 * @return linkid that was created by the insert.
	 */
	public long addLinkToMetadata(long metid, Link link);
	
	/**
	 * Add a Link object to a Person object by the person id.
	 * @param perid
	 * @param link
	 * @return linkid that was created by the insert.
	 */
	public long addLinkToPerson(long perid, Link link);
	
	/**
	 * Add a Link object to a Route object by the route id.
	 * @param rteid from the route table
	 * @param link
	 * @return linkid that was created by the insert.
	 */
	public long addLinkToRte(long rteid, Link link);
	
	/**
	 * Add a Link object to a Rtept object by the route point id.
	 * @param rptid from the route point table
	 * @param link
	 * @return linkid that was created by the insert.
	 */
	public long addLinkToRtept(long rptid, Link link);
	
	/**
	 * Add a Link object to a Track object by the track id.
	 * @param trkid from the track table
	 * @param link
	 * @return linkid that was created by the insert.
	 */
	public long addLinkToTrk(long trkid, Link link);
	
	/**
	 * Add a Link object to a Trkpt object by the track point id.
	 * @param tptid from the track point table
	 * @param link
	 * @return linkid that was created by the insert.
	 */
	public long addLinkToTrkpt(long tptid, Link link);
	
	/**
	 * Add a Link object to a Wpt object by the way point id.
	 * @param wptid from the way point table
	 * @param link
	 * @return linkid that was created by the insert.
	 */
	public long addLinkToWpt(long wptid, Link link);
	
	/**
	 * Delete a Link from the able by its linkid.
	 * @param linkid
	 */
	public void delete(long linkid);
	
	/**
	 * findById
	 * @param linkid from link table
	 * @return Link
	 */
	public Link findById(long linkid);
	
	/**
	 * Total count in the link table.
	 * @return long
	 */
	public long getTotalCount();
	
	/**
	 * Fetch one long ArrayList of Link.
	 * @return
	 */
	public ArrayList<Link> listAll();
	
	/**
	 * Fetch ArrayList of Link objects, paged.
	 * @param start First page.
	 * @param offset Number per page.
	 * @param orderby Sort order = [type | linkid]
	 * @return
	 */
	public ArrayList<Link> listAllPaged(long start, long offset, String orderby);
	public void update(Link link);
	
}
