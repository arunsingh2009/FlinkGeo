package com.pojo.master;

public class GeoFeature {

	private Geometry geometry;

	private String type;

	private String properties;

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "ClassPojo [geometry = " + geometry + ", type = " + type + ", properties = " + properties + "]";
	}
}
