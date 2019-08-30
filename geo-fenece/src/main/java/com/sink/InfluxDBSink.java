package com.sink;

import java.util.concurrent.TimeUnit;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.util.CollectionUtil;
import org.apache.flink.util.StringUtils;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

import com.pojo.InfluxDBPoint;

public class InfluxDBSink extends RichSinkFunction<InfluxDBPoint> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient InfluxDB influxDB = null;
	private final static String DEFAULT_DATABASE_NAME = "ams";
	private final static String DEFAULT_FIELD_NAME = "value";
	private String measurement;
	private String fieldName;

	public InfluxDBSink(String measurement){
		this.measurement = measurement;
		
	}

	@Override
	public void open(Configuration unused) throws Exception {
		super.open(unused);
		influxDB = InfluxDBFactory.connect("http://localhost:8086",
				"admin",
				"admin");
		influxDB.createDatabase(DEFAULT_DATABASE_NAME);
		influxDB.enableBatch(2000, 100, TimeUnit.MILLISECONDS);
	}

	@Override
	public void close() throws Exception {
		super.close();
	}

	@Override
	public void invoke(InfluxDBPoint dataPoint, SinkFunction.Context context) throws Exception {
		
		if (StringUtils.isNullOrWhitespaceOnly(dataPoint.getMeasurement())) {
            throw new RuntimeException("No measurement defined");
        }

        Point.Builder builder = Point.measurement(measurement)
                .time(dataPoint.getTimestamp(), TimeUnit.MILLISECONDS);

        if (!CollectionUtil.isNullOrEmpty(dataPoint.getFields())) {
            builder.fields(dataPoint.getFields());
        }

        if (!CollectionUtil.isNullOrEmpty(dataPoint.getTags())) {
            builder.tag(dataPoint.getTags());
        }

        Point point = builder.build();

		influxDB.write(DEFAULT_DATABASE_NAME, "autogen", point);
	}
}
