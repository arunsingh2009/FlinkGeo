package com.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class Telemetry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Header header;

	private Location location;
	
	private String organization;
	
	private String origin;
	
	private ArrayList<String> machingSubList;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public ArrayList<String> getMachingSubList() {
		return machingSubList;
	}

	public void setMachingSubList(ArrayList<String> machingSubList) {
		this.machingSubList = machingSubList;
	}

	@Override
	public String toString() {
		return "Telemetry [header=" + header + ", location=" + location + ", organization=" + organization + ", origin="
				+ origin + ", machingSubList=" + machingSubList + "]";
	}

	
}
