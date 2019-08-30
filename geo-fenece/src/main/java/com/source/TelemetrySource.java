package com.source;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import com.pojo.Header;
import com.pojo.Location;
import com.pojo.Telemetry;

public class TelemetrySource implements SourceFunction<Telemetry> {

	/**
	 * 
	 */
	private volatile boolean isRunning = true;
	private static final long serialVersionUID = 1L;
	private static final long WAITTIME = 2000L;
	private static long count=0;
	public void cancel() {
		isRunning = false;
	}

	public void run(SourceContext<Telemetry> context) throws Exception {
		System.out.println(" TelemetrySource count....");
		while (isRunning) {
			
			Telemetry telemetry = createTelemetry("23");
			Telemetry telemetry0 = createTelemetry("24");
			Telemetry telemetry1 = createTelemetry("25");
			Telemetry telemetry2 = createTelemetry("26");
			Telemetry telemetry3 = createTelemetry("27");
			/*context.collectWithTimestamp(telemetry, System.currentTimeMillis());
			context.collectWithTimestamp(telemetry0, System.currentTimeMillis());
			context.collectWithTimestamp(telemetry1, System.currentTimeMillis());
			context.collectWithTimestamp(telemetry2, System.currentTimeMillis());
			context.collectWithTimestamp(telemetry3, System.currentTimeMillis());
			*/
			/*context.collectWithTimestamp(createTelemetry("25"), System.currentTimeMillis());
			context.collectWithTimestamp(createTelemetry("24"), System.currentTimeMillis());
			context.collectWithTimestamp(createTelemetry("23"), System.currentTimeMillis());
			context.collectWithTimestamp(createTelemetry("22"), System.currentTimeMillis());*/
			context.collectWithTimestamp(createTelemetry("21"), System.currentTimeMillis());
			context.collectWithTimestamp(createTelemetry("20"), System.currentTimeMillis());
			Thread.sleep(WAITTIME);
			/*executor.submit(() -> {
				
			    return null;
			});*/
			/*executor.submit(() -> {
				Telemetry telemetry = createTelemetry("25");
				context.collectWithTimestamp(telemetry, System.currentTimeMillis());
				Thread.sleep(WAITTIME);
			    return null;
			});
			executor.submit(() -> {
				Telemetry telemetry = createTelemetry("26");
				context.collectWithTimestamp(telemetry, System.currentTimeMillis());
				Thread.sleep(WAITTIME);
			    return null;
			});	*/
		}
	}

	private static Telemetry createTelemetry(String id) {
		Telemetry telemetry = new Telemetry();
		/*String[] ids = { id, id};
		int x = ThreadLocalRandom.current().nextInt(2);*/
		String assetId = "Asset" + id;
		String assetType = "Truck";
		if (count == 0) {
			Location loc = createLocation("-80.724078", "35.265254");
			telemetry.setLocation(loc);
			count=1;
		} else if(count == 1){
			Location loc = createLocation("-80.835564", "35.399172");
			telemetry.setLocation(loc);
			count=0;
		}
		Header header = createHeader("Z99", assetId, assetId, assetType);
		telemetry.setHeader(header);
		return telemetry;

	}

	private static Header createHeader(String make, String assetKey, String serialNumber, String assetType) {

		final String messageId = UUID.randomUUID().toString();
		String[] subscriptionRoles = { "eroutes", "AMS" };
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Instant instant = timestamp.toInstant();
		Header header = new Header();
		header.setAssetId(assetType + make);
		header.setAssetKey(assetKey);
		header.setMake(make);
		header.setMessageId(messageId);
		header.setMessageTimestamp(instant.toString());
		header.setContent("loc");
		header.setSubscriptionRoles(subscriptionRoles);
		header.setSerialNumber(serialNumber);
		header.setAssetType(assetType);
		return header;

	}

	private static Location createLocation(String latitude, String longitude) {
		Location loc = new Location();
		loc.setLatitude(latitude);
		loc.setLogitude(longitude);
		return loc;

	}

	private static Location createLocation() {
		Location loc = new Location();
		Double latitude = ThreadLocalRandom.current().nextDouble(35.04428, 35.046036);
		Double longitude = ThreadLocalRandom.current().nextDouble(-80.664582, -80.662586);
		loc.setLatitude(latitude.toString());
		loc.setLogitude(longitude.toString());
		return loc;

	}

}
