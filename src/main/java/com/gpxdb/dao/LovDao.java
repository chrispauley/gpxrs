package com.gpxdb.dao;


import java.util.ArrayList;


import com.gpxdb.model.lov.LovLink;

public interface LovDao  {

	public ArrayList<LovLink> getLovLinks();
	public LovLink findById(long lovid);
}
