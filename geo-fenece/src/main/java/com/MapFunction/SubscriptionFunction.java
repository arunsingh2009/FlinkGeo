package com.MapFunction;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.co.RichCoFlatMapFunction;
import org.apache.flink.util.Collector;

import com.pojo.Subscription;
import com.pojo.Telemetry;

public class SubscriptionFunction extends RichCoFlatMapFunction<Telemetry, Subscription, Telemetry> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// keyed, managed state
	@SuppressWarnings("unused")
	private ValueState<Telemetry> telemetryState;
	private ValueState<Subscription> subscriptionState;

	@Override
	public void open(Configuration config) {
		telemetryState = getRuntimeContext()
				.getState(new ValueStateDescriptor<>("saved telemetry", Telemetry.class));
		subscriptionState = getRuntimeContext()
				.getState(new ValueStateDescriptor<>("subscription", Subscription.class));
	}

	@Override
	public void flatMap1(Telemetry telemtry, Collector<Telemetry> out) throws Exception {
		Subscription subscription = subscriptionState.value();
		if (subscription != null) {
			telemtry.setOrganization(subscription.getOrganization());
			telemtry.setOrigin(subscription.getOrigin());
			out.collect(telemtry);
		} else {
			System.out.println("No Subscription Data Found ->" + telemtry.getHeader().getAssetKey());
		}
	}

	@Override
	public void flatMap2(Subscription deal, Collector<Telemetry> out) throws Exception {
		System.out.println("SubscriptionFunction flatMap2....");
		subscriptionState.update(deal);
	}

}