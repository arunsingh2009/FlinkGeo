package com.pojo;

import java.io.Serializable;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String logitude;

	private String latitude;
	
	 /** The geometry. */
	private Geometry geometry;

	public String getLogitude() {
		return logitude;
	}

	public void setLogitude(String logitude) {
		this.logitude = logitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	   * Gets the geometry.
	   *
	   * @return the geometry
	   */
	public Geometry getGeometry() {
	    Double newLatitude = Double.parseDouble(this.latitude);
	    Double newLongitude = Double.parseDouble(this.logitude);
	    Coordinate coordinates = new Coordinate(newLatitude, newLongitude);
	    this.geometry = new GeometryFactory().createPoint(coordinates);
	    return geometry;
	}

	@Override
	public String toString() {
		return "Location [logitude=" + logitude + ", latitude=" + latitude + ", geometry=" + geometry + "]";
	}

	
}
