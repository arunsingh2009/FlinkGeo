package com.job;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig.ExternalizedCheckpointCleanup;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import com.MapFunction.GeoFenceFunction;
import com.MapFunction.MasterDataFunction;
import com.MapFunction.MatchingSubsAssetFunction;
import com.MapFunction.SubscriptionFunction;
import com.google.gson.JsonObject;
import com.pojo.InfluxDBPoint;
import com.pojo.Subscription;
import com.pojo.Telemetry;
import com.pojo.master.MasterData;
import com.sink.InfluxDBSink;
import com.source.MasterDataSource;
import com.source.SubscriptionStream;
import com.source.TelemetrySource;
import com.windowfunction.AlertTypeProcessFunction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class GeoMain {

	public static final Logger LOG = Logger.getLogger(GeoMain.class.getName());
	public static final String GEO_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER, true);
		StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(config);
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		/*env.setStateBackend(new FsStateBackend("file:///C:/arun/flink/statebackend/geo/checkpoints"));

		// env.setStateBackend(new
		// RocksDBStateBackend("file:///path/to/checkpoint", true));

		// start a checkpoint every 1000 ms
		env.enableCheckpointing(1000);

		// advanced options:

		// set mode to exactly-once (this is the default)
		env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);

		// make sure 500 ms of progress happen between checkpoints
		env.getCheckpointConfig().setMinPauseBetweenCheckpoints(500);

		// checkpoints have to complete within one minute, or are discarded
		env.getCheckpointConfig().setCheckpointTimeout(60000);

		// allow only one checkpoint to be in progress at the same time
		env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);

		// enable externalized checkpoints which are retained after job
		// cancellation
		env.getCheckpointConfig().enableExternalizedCheckpoints(ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);*/

		DataStream<Subscription> subscriptionStream = env.addSource(new SubscriptionStream(), "SubscriptionStream");

		DataStream<Telemetry> tsfStream = env.addSource(new TelemetrySource(), "TelemetryStream");

		DataStream<MasterData> masterDataStream = env.addSource(new MasterDataSource(), "MasterDataStream");

		// Store The Subscription Data
		DataStream<InfluxDBPoint> subscriptionSink = subscriptionStream
				.map(new RichMapFunction<Subscription, InfluxDBPoint>() {

					@Override
					public InfluxDBPoint map(Subscription value) throws Exception {

						String measurement = "MasterData";
						long timestamp = System.currentTimeMillis();

						HashMap<String, String> tags = new HashMap<>();
						tags.put("assetKey", value.getAssetKey());
						tags.put("serialNumber", value.getSerialNumber());
						tags.put("make", value.getMake());

						HashMap<String, Object> fields = new HashMap<>();
						tags.put("origin", value.getOrigin());
						tags.put("serialNumber", value.getSerialNumber());
						tags.put("make", value.getMake());
						fields.put("organization", value.getOrganization());
						fields.put("organizationType", value.getOrganizationType());
						fields.put("status", value.getStatus());
						fields.put("assetKey", value.getAssetKey());
						fields.put("CreatedDate", value.getCreatedDate());
						fields.put("UpdatedDate", value.getUpdatedDate());
						fields.put("applicationName", value.getApplicationName());
						fields.put("startTime", value.getStartTime());
						System.out.println(" Trasfermation ");
						return new InfluxDBPoint(measurement, timestamp, tags, fields);
					}

				});

		// subscriptionSink.addSink(new InfluxDBSink("subscription"));

		DataStream<MasterData> masterStream = masterDataStream.keyBy(new KeySelector<MasterData, String>() {
			private static final long serialVersionUID = -4873366487571254798L;

			@Override
			public String getKey(MasterData value) throws Exception {
				return value.getDevice()[0].getSerialNumber() + value.getDevice()[0].getMake();
			}
		}).connect(subscriptionStream.keyBy(new KeySelector<Subscription, String>() {
			private static final long serialVersionUID = -4873366487571254798L;

			@Override
			public String getKey(Subscription value) throws Exception {
				return value.getSerialNumber() + value.getMake();
			}
		})).flatMap(new MasterDataFunction());

		// Filter The tsf stream for loc and truck assetType only
		DataStream<Telemetry> telemetryStream = tsfStream.filter(new FilterFunction<Telemetry>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean filter(Telemetry telemetry) throws Exception {
				return "Truck".equalsIgnoreCase(telemetry.getHeader().getAssetType())
						&& telemetry.getHeader().getContent().contains("loc");
			}
		});

		// Join the subscription stream with telemetry stream to get
		// Organization and Origin
		DataStream<Telemetry> tsfOrganizationStream = telemetryStream.keyBy(new KeySelector<Telemetry, String>() {
			private static final long serialVersionUID = -4873366487571254798L;

			@Override
			public String getKey(Telemetry value) throws Exception {
				return value.getHeader().getSerialNumber() + value.getHeader().getMake();
			}
		}).connect(subscriptionStream.keyBy(new KeySelector<Subscription, String>() {
			private static final long serialVersionUID = -4873366487571254798L;

			@Override
			public String getKey(Subscription value) throws Exception {
				return value.getSerialNumber() + value.getMake();
			}
		})).flatMap(new SubscriptionFunction());

		// Join the subscription stream with streamOne to get matching subasset
		// list
		DataStream<Telemetry> tsfSubStream = tsfOrganizationStream.keyBy(new KeySelector<Telemetry, String>() {
			private static final long serialVersionUID = -4873366487571254798L;

			@Override
			public String getKey(Telemetry value) throws Exception {
				return value.getOrganization() + value.getOrigin();
			}
		}).connect(subscriptionStream.keyBy(new KeySelector<Subscription, String>() {
			private static final long serialVersionUID = -4873366487571254798L;

			@Override
			public String getKey(Subscription value) throws Exception {
				return value.getOrganization() + value.getOrigin();
			}
		})).flatMap(new MatchingSubsAssetFunction());

		DataStream<JsonObject> geoAlert = tsfSubStream.keyBy(tsf -> tsf.getOrganization())
				.connect(masterStream.keyBy(master -> master.getOrganization())).flatMap(new GeoFenceFunction());

		DataStream<InfluxDBPoint> geoAlertSink = geoAlert.map(new RichMapFunction<JsonObject, InfluxDBPoint>() {

			@Override
			public InfluxDBPoint map(JsonObject value) throws Exception {

				String measurement = "GeoAlert";
				long timestamp = System.currentTimeMillis();

				HashMap<String, String> tags = new HashMap<>();
				tags.put("geoBoundaryAssetDeviceSerialNumber",
						value.get("geoBoundaryAssetDeviceSerialNumber").getAsString());
				tags.put("assetDeviceSerialNumber", value.get("assetDeviceSerialNumber").getAsString());
				tags.put("assetDeviceMake", value.get("assetDeviceMake").getAsString());
				tags.put("alertType", value.get("alertType").getAsString());

				HashMap<String, Object> fields = new HashMap<>();
				fields.put("alertType", value.get("alertType").getAsString());
				fields.put("assetDeviceSerialNumber", value.get("assetDeviceSerialNumber").getAsString());
				fields.put("assetDeviceMake", value.get("assetDeviceMake").getAsString());
				fields.put("alertTimestamp", value.get("alertTimestamp").getAsString());
				fields.put("assetLocationTimestamp", value.get("assetLocationTimestamp").getAsString());
				fields.put("geoBoundaryAssetDeviceSerialNumber",
						value.get("geoBoundaryAssetDeviceSerialNumber").getAsString());
				fields.put("geoBoundaryAssetType", value.get("geoBoundaryAssetType").getAsString());
				fields.put("assetType", value.get("assetType").getAsString());

				//System.out.println(" Trasfermation Geo Alert ");
				return new InfluxDBPoint(measurement, timestamp, tags, fields);
			}

		});
		geoAlertSink.addSink(new InfluxDBSink("GeoAlert"));
		
		geoAlert.assignTimestampsAndWatermarks(new AscendingTimestampExtractor<JsonObject>() {

	        @Override
	        public long extractAscendingTimestamp(JsonObject element) {
	            return getEpochTime(element.get("assetLocationTimestamp").getAsString());
	        }
		});

		DataStream<JsonObject> stateMissMatch = geoAlert.keyBy(new KeySelector<JsonObject, String>() {
			private static final long serialVersionUID = -4873366487571254798L;

			@Override
			public String getKey(JsonObject value) throws Exception {
				return value.get("assetDeviceSerialNumber").getAsString()
						+ value.get("geoBoundaryAssetDeviceSerialNumber").getAsString();
			}
		}).window(TumblingEventTimeWindows.of(Time.seconds(5)))
				.process(new AlertTypeProcessFunction());

		stateMissMatch.print();
		
		try {
			env.execute("Geo Stream");
		}catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}

	}

	public static long getEpochTime(String dateTime){
	    String zd = ZonedDateTime.parse(dateTime)
	        .format(DateTimeFormatter.ofPattern(GEO_DATETIME_FORMAT));
	    Date data = null;
		try {
			data = new SimpleDateFormat(GEO_DATETIME_FORMAT).parse(zd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return data.getTime();
	  }
}


