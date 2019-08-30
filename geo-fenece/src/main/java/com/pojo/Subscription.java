package com.pojo;

import java.io.Serializable;

public class Subscription implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String serialNumber;

	private String level;

	private String associatedOrganization;

	private String updatedDate;

	private String origin;

	private String catalogApplicationName;

	private String type;

	private String assetKey;

	private String organizationType;

	private String dealerCustomerNumber;

	private String organization;

	private String createdDate;

	private String model;

	private String typeId;

	private String startTime;

	private String id;

	private String make;

	private String applicationName;

	private String status;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAssociatedOrganization() {
		return associatedOrganization;
	}

	public void setAssociatedOrganization(String associatedOrganization) {
		this.associatedOrganization = associatedOrganization;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String UpdatedDate) {
		this.updatedDate = UpdatedDate;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getCatalogApplicationName() {
		return catalogApplicationName;
	}

	public void setCatalogApplicationName(String catalogApplicationName) {
		this.catalogApplicationName = catalogApplicationName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAssetKey() {
		return assetKey;
	}

	public void setAssetKey(String assetKey) {
		this.assetKey = assetKey;
	}

	public String getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}

	public String getDealerCustomerNumber() {
		return dealerCustomerNumber;
	}

	public void setDealerCustomerNumber(String dealerCustomerNumber) {
		this.dealerCustomerNumber = dealerCustomerNumber;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String CreatedDate) {
		this.createdDate = CreatedDate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ClassPojo [serialNumber = " + serialNumber + ", level = " + level + ", associatedOrganization = "
				+ associatedOrganization + ", UpdatedDate = " + updatedDate + ", origin = " + origin
				+ ", catalogApplicationName = " + catalogApplicationName + ", type = " + type + ", assetKey = "
				+ assetKey + ", organizationType = " + organizationType + ", dealerCustomerNumber = "
				+ dealerCustomerNumber + ", organization = " + organization + ", CreatedDate = " + createdDate
				+ ", model = " + model + ", typeId = " + typeId + ", startTime = " + startTime + ", id = " + id
				+ ", make = " + make + ", applicationName = " + applicationName + ", status = " + status + "]";
	}
}
