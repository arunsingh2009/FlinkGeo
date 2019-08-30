package com.pojo.master;

import java.io.Serializable;
import java.util.Arrays;

public class MasterData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String owner;
	
	private String organization;

	private String dailyTons;

	private String _rid;

	private String productionRate;

	private Address address;

	private String contactName;

	private String siloStorage;

	private String description;

	private String timeZone;

	private String assetType;

	private String plantAffiliationType;

	private String plantDeviceSerialNumber;

	private String _attachments;

	private String assetId;

	private String plantCustomerId;

	private String operationType;

	private String id;

	private String contactPhone;

	private Device[] device;

	private String applicationName;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDailyTons() {
		return dailyTons;
	}

	public void setDailyTons(String dailyTons) {
		this.dailyTons = dailyTons;
	}

	public String get_rid() {
		return _rid;
	}

	public void set_rid(String _rid) {
		this._rid = _rid;
	}

	public String getProductionRate() {
		return productionRate;
	}

	public void setProductionRate(String productionRate) {
		this.productionRate = productionRate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getSiloStorage() {
		return siloStorage;
	}

	public void setSiloStorage(String siloStorage) {
		this.siloStorage = siloStorage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getPlantAffiliationType() {
		return plantAffiliationType;
	}

	public void setPlantAffiliationType(String plantAffiliationType) {
		this.plantAffiliationType = plantAffiliationType;
	}

	public String getPlantDeviceSerialNumber() {
		return plantDeviceSerialNumber;
	}

	public void setPlantDeviceSerialNumber(String plantDeviceSerialNumber) {
		this.plantDeviceSerialNumber = plantDeviceSerialNumber;
	}

	public String get_attachments() {
		return _attachments;
	}

	public void set_attachments(String _attachments) {
		this._attachments = _attachments;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getPlantCustomerId() {
		return plantCustomerId;
	}

	public void setPlantCustomerId(String plantCustomerId) {
		this.plantCustomerId = plantCustomerId;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Device[] getDevice() {
		return device;
	}

	public void setDevice(Device[] device) {
		this.device = device;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "MasterData [owner=" + owner + ", organization=" + organization + ", dailyTons=" + dailyTons + ", _rid="
				+ _rid + ", productionRate=" + productionRate + ", address=" + address + ", contactName=" + contactName
				+ ", siloStorage=" + siloStorage + ", description=" + description + ", timeZone=" + timeZone
				+ ", assetType=" + assetType + ", plantAffiliationType=" + plantAffiliationType
				+ ", plantDeviceSerialNumber=" + plantDeviceSerialNumber + ", _attachments=" + _attachments
				+ ", assetId=" + assetId + ", plantCustomerId=" + plantCustomerId + ", operationType=" + operationType
				+ ", id=" + id + ", contactPhone=" + contactPhone + ", device=" + Arrays.toString(device)
				+ ", applicationName=" + applicationName + "]";
	}
	
	
}
