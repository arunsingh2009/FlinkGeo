package com.MapFunction;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.metrics.Counter;
import org.apache.flink.streaming.api.functions.co.RichCoFlatMapFunction;
import org.apache.flink.util.Collector;
import com.enums.GeoAlertType;
import com.enums.GeoStatus;
import com.google.gson.JsonObject;
import com.pojo.Telemetry;
import com.pojo.master.Device;
import com.pojo.master.GeofenceList;
import com.pojo.master.MasterData;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;


public class GeoFenceFunction extends RichCoFlatMapFunction<Telemetry, MasterData, JsonObject> {

	private static final long serialVersionUID = 1L;
	// keyed, managed state
	private MapState<String, ArrayList> subscriptionState;
	 public static final String STATIC = "STATIC";

	private MapState<String, Integer> fenceState;
	
	private Counter counter;

	@Override
	public void open(Configuration config) {
		MapStateDescriptor<String, Integer> fenceDescriptor = new MapStateDescriptor<>("modelState", String.class,
				Integer.class);
		fenceState = getRuntimeContext().getMapState(fenceDescriptor);

		MapStateDescriptor<String, ArrayList> descriptor = new MapStateDescriptor<>("modelState", String.class,
				ArrayList.class);
		subscriptionState = getRuntimeContext().getMapState(descriptor);
		
		this.counter = getRuntimeContext()
			      .getMetricGroup()
			      .counter("geoAlertCount");
	}

	@Override
	public void flatMap1(Telemetry telemetry, Collector<JsonObject> out) throws Exception {

		
		Geometry eventGeoPoint = telemetry.getLocation().getGeometry();
		ArrayList<MasterData> masterList = subscriptionState.get(telemetry.getOrganization());
		if (masterList != null) {
			Iterator<MasterData> interator = masterList.iterator();
			while (interator.hasNext()) {
				MasterData mData = interator.next();
				Device device=mData.getDevice()[0];
				GeofenceList[] listArray=device.getGeofenceList();
				List<GeofenceList> list = Arrays.asList(listArray); 
				/*list.stream().forEach(action);*/
				for (GeofenceList geoFence :list){
				if (STATIC.equalsIgnoreCase(geoFence.getGeofenceType())) {
					 String stateId = telemetry.getHeader().getSerialNumber() + geoFence.getId() + device.getSerialNumber();
					 Integer state =fenceState.get(stateId);
					 Coordinate[] arrayCordinate= geoFence.getGeoFeature().getGeometry().getCoordinateList();
					 Geometry polygoenPlant = new GeometryFactory().createPolygon(arrayCordinate);
					 if (isNullOrEmpty(state)) {
					      state = 0;
					    }
					// System.out.println("Final Data " + telemetry.getHeader().getSerialNumber() +" State " + state + " Contains "+ polygoenPlant.contains(eventGeoPoint));
					 if("POLYGON".equalsIgnoreCase(geoFence.getGeoFeature().getGeometry().getType())){
						 if (polygoenPlant.contains(eventGeoPoint) && (state == GeoStatus.EXIT.getValue())) {
							 fenceState.put(stateId, 1);
							 JsonObject alert= new JsonObject();
							 alert.addProperty("alertType", GeoAlertType.ENTER.name());
							 alert.addProperty("assetDeviceSerialNumber", telemetry.getHeader().getSerialNumber());
							 alert.addProperty("assetDeviceMake", telemetry.getHeader().getMake());
							 alert.addProperty("alertTimestamp", ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).toString());
							 alert.addProperty("assetLocationTimestamp", telemetry.getHeader().getMessageTimestamp());
							 alert.addProperty("geoBoundaryAssetDeviceSerialNumber", device.getSerialNumber());
							 alert.addProperty("geoBoundaryAssetType", device.getType());
							 alert.addProperty("assetType", telemetry.getHeader().getAssetType());
							 this.counter.inc();
							 out.collect(alert);
						 }else if(!polygoenPlant.contains(eventGeoPoint) && GeoStatus.ENTRY.getValue() == state){
							 fenceState.put(stateId, 0);
							 JsonObject alert= new JsonObject();
							 alert.addProperty("alertType", GeoAlertType.EXIT.name());
							 alert.addProperty("assetDeviceSerialNumber", telemetry.getHeader().getSerialNumber());
							 alert.addProperty("assetDeviceMake", telemetry.getHeader().getMake());
							 alert.addProperty("alertTimestamp", ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).toString());
							 alert.addProperty("assetLocationTimestamp", telemetry.getHeader().getMessageTimestamp());
							 alert.addProperty("geoBoundaryAssetDeviceSerialNumber", device.getSerialNumber());
							 alert.addProperty("geoBoundaryAssetType",device.getType());
							 alert.addProperty("assetType", telemetry.getHeader().getAssetType());
							 this.counter.inc();
							 out.collect(alert);
						 }
					 }
				 }
				}
			}
		}
	}

	@Override
	public void flatMap2(MasterData masterData, Collector<JsonObject> out) throws Exception {
		System.out.println("GeoFenceFunction flatMap2....");
		ArrayList<MasterData> masterList = subscriptionState.get(masterData.getOrganization());
		if(!"Truck".equalsIgnoreCase(masterData.getAssetType())){
		if (masterList != null) {
			masterList.add(masterData); // TODO add only paver and plant
			subscriptionState.put(masterData.getOrganization(), masterList);
		} else {
			ArrayList<MasterData> list = new ArrayList<>();
			list.add(masterData); // TODO
			subscriptionState.put(masterData.getOrganization(), list);
		}
	}
	}
	private static boolean isNullOrEmpty(Integer str) {
	    if (str != null) {
	      return false;
	    }
	    return true;
	  }
}
