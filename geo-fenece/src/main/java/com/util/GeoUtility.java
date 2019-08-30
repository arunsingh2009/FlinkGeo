package com.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.pojo.Subscription;
import com.pojo.master.Device;
import com.pojo.master.MasterData;

public class GeoUtility {
	
	public static Subscription getSubscription(Object document){
		Subscription subscription = new Subscription();
		subscription.setApplicationName(JsonPath.read(document, "$.applicationName"));
		subscription.setAssetKey(JsonPath.read(document, "$.assetKey"));
		subscription.setAssociatedOrganization(JsonPath.read(document, "$.associatedOrganization"));
		subscription.setCatalogApplicationName(JsonPath.read(document, "$.catalogApplicationName"));
		subscription.setCreatedDate(JsonPath.read(document, "$.CreatedDate"));
		subscription.setDealerCustomerNumber(JsonPath.read(document, "$.dealerCustomerNumber"));
		subscription.setId(JsonPath.read(document, "$.id"));
		subscription.setLevel(JsonPath.read(document, "$.level"));
		subscription.setMake(JsonPath.read(document, "$.make"));
		subscription.setModel(JsonPath.read(document, "$.model"));
		subscription.setOrganization(JsonPath.read(document, "$.organization"));
		subscription.setOrganizationType(JsonPath.read(document, "$.organizationType"));
		subscription.setOrigin(JsonPath.read(document, "$.origin"));
		subscription.setSerialNumber(JsonPath.read(document, "$.serialNumber"));
		subscription.setStartTime(JsonPath.read(document, "$.startTime"));
		subscription.setStatus(JsonPath.read(document, "$.status"));
		subscription.setType(JsonPath.read(document, "$.type"));
		subscription.setTypeId(JsonPath.read(document, "$.typeId"));
		subscription.setUpdatedDate(JsonPath.read(document, "$.UpdatedDate"));
		return subscription;
		
	}
	
	public static MasterData getMasterData(Object document) throws JsonParseException, JsonMappingException, IOException{
		MasterData masterdata= new MasterData();
		LinkedHashMap<?, ?> geofenecList=JsonPath.read(document, "$.device[0]");
		ObjectMapper mapper = new ObjectMapper();
		Device device =mapper.convertValue(geofenecList, Device.class);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		masterdata.setApplicationName(JsonPath.read(document, "$.applicationName"));
		masterdata.setAssetId(JsonPath.read(document, "$.assetId"));
		masterdata.setAssetType(JsonPath.read(document, "$.assetType"));
		masterdata.setDevice(new Device[]{device});
		
		return masterdata;
	}

}
