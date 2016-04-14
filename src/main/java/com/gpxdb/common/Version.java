package com.gpxdb.common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
/**
 * @author Chris Pauley
 *
 */

@XmlRootElement
public class Version {

	protected static String MAJOR_VER = "0";

	protected static String MINOR_VER = "9";

	protected static String REVISION = "5";
//	public static String DEV_STAGE_0 = "a";
//	public static String DEV_STAGE_1 = "b";
//	public static String DEV_STAGE_2 = "rc";
//	public static String DEV_STAGE_3 = "r";
	
	private String major = "";
	private String minor = "";
	private String update = "";

	public Version() {
		major = Version.MAJOR_VER;
		minor = Version.MINOR_VER;
		update = Version.REVISION;
	}

	public Version(String major, String minor, String update) {
		super();
		this.major = major;
		this.minor = minor;
		this.update = update;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getVersion() {
		return major + "." + minor + "." + update;
	}

	public static String getLongVersion(){
		return Version.MAJOR_VER + "." + Version.MINOR_VER + "."
		+ Version.REVISION;		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Version.MAJOR_VER + "." + Version.MINOR_VER + "."
				+ Version.REVISION);
	}
}
