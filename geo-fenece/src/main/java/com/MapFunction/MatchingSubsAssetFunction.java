package com.MapFunction;

import java.util.ArrayList;

import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.co.RichCoFlatMapFunction;
import org.apache.flink.util.Collector;

import com.pojo.Subscription;
import com.pojo.Telemetry;

public class MatchingSubsAssetFunction extends RichCoFlatMapFunction<Telemetry, Subscription, Telemetry> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// keyed, managed state
	private MapState<String, ArrayList> subscriptionState;

	// private ValueState<Subscription> subscriptionState;

	@Override
	public void open(Configuration config) {
		MapStateDescriptor<String, ArrayList> descriptor = new MapStateDescriptor<>("modelState", String.class,
				ArrayList.class);
		subscriptionState = getRuntimeContext().getMapState(descriptor);
	}

	@Override
	public void flatMap1(Telemetry telemtry, Collector<Telemetry> out) throws Exception {
		ArrayList<String> machingSubList = subscriptionState.get(telemtry.getOrganization());
		if (machingSubList != null) {
			machingSubList.remove(telemtry.getHeader().getSerialNumber());
			telemtry.setMachingSubList(machingSubList);
			out.collect(telemtry);
		} else {
			System.out.println("No SubscriptionList Data Found ->" + telemtry.getHeader().getAssetKey());
		}
	}

	@Override
	public void flatMap2(Subscription deal, Collector<Telemetry> out) throws Exception {
		System.out.println(" MatchingSubsAssetFunction flatMap2....");
		ArrayList<String> subscription = subscriptionState.get(deal.getOrganization());
		if (subscription != null) {
			subscription.add(deal.getSerialNumber());
			subscriptionState.put(deal.getOrganization(), subscription);
		} else {
			ArrayList<String> list = new ArrayList<String>();
			list.add(deal.getSerialNumber());
			subscriptionState.put(deal.getOrganization(), list);
		}
	}

}
