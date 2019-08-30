package com.pojo.master;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Device implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String serialNumber;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String subscriptionStatus;

	private GeofenceList[] geofenceList;

	private Groups[] groups;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String id;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String type;

	private String make;

	private String deviceSerialNumber;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String ucid;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public GeofenceList[] getGeofenceList() {
		return geofenceList;
	}

	public void setGeofenceList(GeofenceList[] geofenceList) {
		this.geofenceList = geofenceList;
	}

	public Groups[] getGroups() {
		return groups;
	}

	public void setGroups(Groups[] groups) {
		this.groups = groups;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}

	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}

	public String getUcid() {
		return ucid;
	}

	public void setUcid(String ucid) {
		this.ucid = ucid;
	}

	@Override
	public String toString() {
		return "ClassPojo [serialNumber = " + serialNumber + ", subscriptionStatus = " + subscriptionStatus
				+ ", geofenceList = " + geofenceList + ", groups = " + groups + ", id = " + id + ", type = " + type
				+ ", make = " + make + ", deviceSerialNumber = " + deviceSerialNumber + ", ucid = " + ucid + "]";
	}
}
