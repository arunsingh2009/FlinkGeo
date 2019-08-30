package com.windowfunction;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction.Context;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

import com.google.gson.JsonObject;

public class AlertTypeProcessFunction extends ProcessWindowFunction<JsonObject, JsonObject, String, TimeWindow> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int counter;
	JsonObject priviousAlert;

	@Override
	public void process(String key, Context context, Iterable<JsonObject> input,
			org.apache.flink.util.Collector<JsonObject> out) throws Exception {
		for (JsonObject in : input) {
			String alert = in.get("alertType").getAsString();
			priviousAlert = in;
			if (priviousAlert != null && alert.equalsIgnoreCase(priviousAlert.get("alertType").getAsString())) {
				JsonObject obj = new JsonObject();
				obj.addProperty("alertType", in.get("alertType").getAsString());
				obj.addProperty("assetDeviceSerialNumber", in.get("assetDeviceSerialNumber").getAsString());
				obj.addProperty("geoBoundaryAssetDeviceSerialNumber",
						in.get("geoBoundaryAssetDeviceSerialNumber").getAsString());
				obj.addProperty("make", in.get("make").getAsString());
				obj.addProperty("eventTime", in.get("assetLocationTimestamp").getAsString());
				obj.addProperty("priviousEventTime", priviousAlert.get("assetLocationTimestamp").getAsString());
				out.collect(obj);
			}
		}

	}
}
