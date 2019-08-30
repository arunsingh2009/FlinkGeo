package com.MapFunction;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.co.RichCoFlatMapFunction;
import org.apache.flink.util.Collector;

import com.pojo.Subscription;
import com.pojo.master.MasterData;

public class MasterDataFunction extends RichCoFlatMapFunction<MasterData, Subscription, MasterData> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// keyed, managed state
	@SuppressWarnings("unused")
	private ValueState<MasterData> telemetryState;
	private ValueState<Subscription> subscriptionState;

	@Override
	public void open(Configuration config) {
		telemetryState = getRuntimeContext()
				.getState(new ValueStateDescriptor<>("saved telemetry", MasterData.class));
		subscriptionState = getRuntimeContext()
				.getState(new ValueStateDescriptor<>("subscription", Subscription.class));
	}

	@Override
	public void flatMap1(MasterData masterdata, Collector<MasterData> out) throws Exception {
		Subscription subscription = subscriptionState.value();
		if (subscription != null) {
			masterdata.setOrganization(subscription.getOrganization());
			//telemtry.setOrigin(subscription.getOrigin());
			out.collect(masterdata);
		} else {
			System.out.println("No Subscription Data Found MasterDataFunction ->" + masterdata.getAssetId());
		}
	}

	@Override
	public void flatMap2(Subscription deal, Collector<MasterData> out) throws Exception {
		System.out.println("MasterDataFunction flatMap2....");
		subscriptionState.update(deal);
	}

}