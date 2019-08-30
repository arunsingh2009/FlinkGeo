/*******************************************************************************
 * Copyright (c) 2018 Caterpillar Inc All rights reserved.
 *******************************************************************************/
package com.pojo;

import com.enums.GeoAlertType;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The Class GeofenceAlert.
 */
public class GeofenceAlert {



  /** The asset type. */
  private String assetType;

  /** The asset device serial number. */
  private String assetDeviceSerialNumber;

  /** The alert timestamp. */
  private String alertTimestamp;

  /** The geo boundary asset type. */
  private String geoBoundaryAssetType;

  /** The geo boundary asset device serial number. */
  private String geoBoundaryAssetDeviceSerialNumber;

  /** The alert type. */
  private String alertType;

  /** The geofence id. */
  private String geofenceId;

  /** The geofence name. */
  private String geofenceName;

  /** The asset location timestamp. */
  private String assetLocationTimestamp;

  /** The application alert type. */
  private String applicationAlertType;

  /** The document type. */
  private String documentType;

  /** The application name. */
  private String applicationName;

  /** The asset device location. */
  private LocationAlert assetDeviceLocation;

  /** The geo boundary asset device location. */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private LocationAlert geoBoundaryAssetDeviceLocation;

  /** The asset device make. */
  private String assetDeviceMake;

  /** The AMS received time. */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String amsReceivedTime;

  /**
   * Constructor for this class.
   */
  public GeofenceAlert() {
    super();
  }

  /**
   * Gets the asset type.
   *
   * @return the asset type
   */
  public String getAssetType() {
    return assetType;
  }

  /**
   * Sets the asset type.
   *
   * @param assetType the new asset type
   */
  public void setAssetType(String assetType) {
    this.assetType = assetType;
  }

  /**
   * Gets the asset device serial number.
   *
   * @return the asset device serial number
   */
  public String getAssetDeviceSerialNumber() {
    return assetDeviceSerialNumber;
  }

  /**
   * Sets the asset device serial number.
   *
   * @param assetDeviceSN the new asset device serial number
   */
  public void setAssetDeviceSerialNumber(String assetDeviceSN) {
    this.assetDeviceSerialNumber = assetDeviceSN;
  }

  /**
   * Gets the alert timestamp.
   *
   * @return the alert timestamp
   */
  public String getAlertTimestamp() {
    return alertTimestamp;
  }

  /**
   * Sets the alert timestamp.
   *
   * @param alertTimestamp the new alert timestamp
   */
  public void setAlertTimestamp(String alertTimestamp) {
    this.alertTimestamp = alertTimestamp;
  }

  /**
   * Gets the geo boundary asset type.
   *
   * @return the geo boundary asset type
   */
  public String getGeoBoundaryAssetType() {
    return geoBoundaryAssetType;
  }

  /**
   * Sets the geo boundary asset type.
   *
   * @param geoBoundaryAssetType the new geo boundary asset type
   */
  public void setGeoBoundaryAssetType(String geoBoundaryAssetType) {
    this.geoBoundaryAssetType = geoBoundaryAssetType;
  }

  /**
   * Gets the geo boundary asset device serial number.
   *
   * @return the geo boundary asset device serial number
   */
  public String getGeoBoundaryAssetDeviceSerialNumber() {
    return geoBoundaryAssetDeviceSerialNumber;
  }

  /**
   * Sets the geo boundary asset device serial number.
   *
   * @param geoBoundaryAssetDeviceSN the new geo boundary asset device serial number
   */
  public void setGeoBoundaryAssetDeviceSerialNumber(String geoBoundaryAssetDeviceSN) {
    this.geoBoundaryAssetDeviceSerialNumber = geoBoundaryAssetDeviceSN;
  }

  /**
   * Gets the alert type.
   *
   * @return the alert type
   */
  public String getAlertType() {
    return alertType;
  }

  /**
   * Sets the alert type.
   *
   * @param alertType the new alert type
   */
  public void setAlertType(GeoAlertType alertType) {
    this.alertType = alertType.name();
  }

  /**
   * Gets the geofence id.
   *
   * @return the geofence id
   */
  public String getGeofenceId() {
    return geofenceId;
  }

  /**
   * Sets the geofence id.
   *
   * @param geofenceId the new geofence id
   */
  public void setGeofenceId(String geofenceId) {
    this.geofenceId = geofenceId;
  }

  /**
   * Gets the geofence name.
   *
   * @return the geofence name
   */
  public String getGeofenceName() {
    return geofenceName;
  }

  /**
   * Sets the geofence name.
   *
   * @param geofenceName the new geofence name
   */
  public void setGeofenceName(String geofenceName) {
    this.geofenceName = geofenceName;
  }

  /**
   * Gets the asset location timestamp.
   *
   * @return the asset location timestamp
   */
  public String getAssetLocationTimestamp() {
    return assetLocationTimestamp;
  }

  /**
   * Sets the asset location timestamp.
   *
   * @param locationTime the new asset location timestamp
   */
  public void setAssetLocationTimestamp(String locationTime) {
    this.assetLocationTimestamp = locationTime;
  }

  /**
   * Gets the document type.
   *
   * @return the document type
   */
  public String getDocumentType() {
    return documentType;
  }

  /**
   * Sets the document type.
   *
   * @param documentType the new document type
   */
  public void setDocumentType(String documentType) {
    this.documentType = documentType;
  }


  /**
   * Gets the application name.
   *
   * @return the application name
   */
  public String getApplicationName() {
    return applicationName;
  }

  /**
   * Sets the application name.
   *
   * @param applicationName the new application name
   */
  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }



  /**
   * Gets the application alert type.
   *
   * @return the application alert type
   */
  public String getApplicationAlertType() {
    return applicationAlertType;
  }

  /**
   * Sets the application alert type.
   *
   * @param applicationAlertType the new application alert type
   */
  public void setApplicationAlertType(String applicationAlertType) {
    this.applicationAlertType = applicationAlertType;
  }

  /**
   * Gets the asset device location.
   *
   * @return the asset device location
   */
  public LocationAlert getAssetDeviceLocation() {
    return assetDeviceLocation;
  }

  /**
   * Sets the asset device location.
   *
   * @param latitude the latitude
   * @param longitude the longitude
   */
  public void setAssetDeviceLocation(String latitude, String longitude) {
    LocationAlert location = new LocationAlert();
    location.setLatitude(latitude);
    location.setLongitude(longitude);
    this.assetDeviceLocation = location;
  }

  /**
   * Gets the geo boundary asset device location.
   *
   * @return the geo boundary asset device location
   */
  public LocationAlert getGeoBoundaryAssetDeviceLocation() {
    return geoBoundaryAssetDeviceLocation;
  }

  /**
   * Sets the geo boundary asset device location.
   *
   * @param latitude the latitude
   * @param longitude the longitude
   */
  public void setGeoBoundaryAssetDeviceLocation(String latitude, String longitude) {
    LocationAlert location = new LocationAlert();
    location.setLatitude(latitude);
    location.setLongitude(longitude);
    this.geoBoundaryAssetDeviceLocation = location;
  }

  /**
   * Gets the asset device make.
   *
   * @return the asset device make
   */
  public String getAssetDeviceMake() {
    return assetDeviceMake;
  }

  /**
   * Sets the asset device make.
   *
   * @param assetDeviceMake the new asset device make
   */
  public void setAssetDeviceMake(String assetDeviceMake) {
    this.assetDeviceMake = assetDeviceMake;
  }

  /**
   * The Class LocationAlert.
   */
  private class LocationAlert {

    /** The latitude. */
    private String latitude;
    
    /** The longitude. */
    private String longitude;

    /**
     * Gets the latitude.
     *
     * @return the latitude
     */
    public String getLatitude() {
      return latitude;
    }

    /**
     * Sets the latitude.
     *
     * @param latitude the new latitude
     */
    public void setLatitude(String latitude) {
      this.latitude = latitude;
    }

    /**
     * Gets the longitude.
     *
     * @return the longitude
     */
    public String getLongitude() {
      return longitude;
    }

    /**
     * Sets the longitude.
     *
     * @param longitude the new longitude
     */
    public void setLongitude(String longitude) {
      this.longitude = longitude;
    }
  }
 
  public String getAmsReceivedTime() {
    return amsReceivedTime;
  }

  public void setAmsReceivedTime(String amsReceivedTime) {
    this.amsReceivedTime = amsReceivedTime;
  }

  @Override
  public String toString() {
    return "GeofenceAlert [assetType=" + assetType + ", assetDeviceSerialNumber="
        + assetDeviceSerialNumber + ", alertTimestamp=" + alertTimestamp + ", geoBoundaryAssetType="
        + geoBoundaryAssetType + ", geoBoundaryAssetDeviceSerialNumber="
        + geoBoundaryAssetDeviceSerialNumber + ", alertType=" + alertType + ", geofenceId="
        + geofenceId + ", geofenceName=" + geofenceName + ", assetLocationTimestamp="
        + assetLocationTimestamp + ", applicationAlertType=" + applicationAlertType
        + ", documentType=" + documentType + ", applicationName=" + applicationName
        + ", assetDeviceLocation=" + assetDeviceLocation + ", geoBoundaryAssetDeviceLocation="
        + geoBoundaryAssetDeviceLocation + ", assetDeviceMake=" + assetDeviceMake
        + ", AMSReceivedTime=" + amsReceivedTime + "]";
  }



}
