package com.pojo;

import java.io.Serializable;
import java.util.Arrays;

public class Header implements Serializable {

	private static final long serialVersionUID = 1L;

	private String deviceType;

	private String serialNumber;

	private String commMode;

	private String utcOffset;

	private String messageId;

	private String isTethered;

	private String[] subscriptionRoles;

	private String deviceId;

	private String content;

	private String assetKey;

	private String commercialWirelessType;

	private String messageType;

	private String assetId;

	private String model;

	private String moduleSerialNumber;

	private String messageTimestamp;

	private String contentType;

	private String make;

	private String fileType;
	
	private String assetType;

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getCommMode() {
		return commMode;
	}

	public void setCommMode(String commMode) {
		this.commMode = commMode;
	}

	public String getUtcOffset() {
		return utcOffset;
	}

	public void setUtcOffset(String utcOffset) {
		this.utcOffset = utcOffset;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getIsTethered() {
		return isTethered;
	}

	public void setIsTethered(String isTethered) {
		this.isTethered = isTethered;
	}

	public String[] getSubscriptionRoles() {
		return subscriptionRoles;
	}

	public void setSubscriptionRoles(String[] subscriptionRoles) {
		this.subscriptionRoles = subscriptionRoles;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAssetKey() {
		return assetKey;
	}

	public void setAssetKey(String assetKey) {
		this.assetKey = assetKey;
	}

	public String getCommercialWirelessType() {
		return commercialWirelessType;
	}

	public void setCommercialWirelessType(String commercialWirelessType) {
		this.commercialWirelessType = commercialWirelessType;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModuleSerialNumber() {
		return moduleSerialNumber;
	}

	public void setModuleSerialNumber(String moduleSerialNumber) {
		this.moduleSerialNumber = moduleSerialNumber;
	}

	public String getMessageTimestamp() {
		return messageTimestamp;
	}

	public void setMessageTimestamp(String messageTimestamp) {
		this.messageTimestamp = messageTimestamp;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	@Override
	public String toString() {
		return "Header [deviceType=" + deviceType + ", serialNumber=" + serialNumber + ", commMode=" + commMode
				+ ", utcOffset=" + utcOffset + ", messageId=" + messageId + ", isTethered=" + isTethered
				+ ", subscriptionRoles=" + Arrays.toString(subscriptionRoles) + ", deviceId=" + deviceId + ", content="
				+ content + ", assetKey=" + assetKey + ", commercialWirelessType=" + commercialWirelessType
				+ ", messageType=" + messageType + ", assetId=" + assetId + ", model=" + model + ", moduleSerialNumber="
				+ moduleSerialNumber + ", messageTimestamp=" + messageTimestamp + ", contentType=" + contentType
				+ ", make=" + make + ", fileType=" + fileType + ", assetType=" + assetType + "]";
	}
	

	
}