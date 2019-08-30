package com.pojo.master;

import com.fasterxml.jackson.annotation.JsonInclude;

public class GeofenceList {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String geofenceType;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String description;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String zoom;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String id;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String title;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private GeoFeature geoFeature;

	public String getGeofenceType() {
		return geofenceType;
	}

	public void setGeofenceType(String geofenceType) {
		this.geofenceType = geofenceType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getZoom() {
		return zoom;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public GeoFeature getGeoFeature() {
		return geoFeature;
	}

	public void setGeoFeature(GeoFeature geoFeature) {
		this.geoFeature = geoFeature;
	}

	@Override
	public String toString() {
		return "ClassPojo [geofenceType = " + geofenceType + ", description = " + description + ", zoom = " + zoom
				+ ", id = " + id + ", title = " + title + ", geoFeature = " + geoFeature + "]";
	}
}
