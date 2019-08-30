package com.source;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.pojo.Subscription;
import com.util.GeoUtility;
import com.util.JMSFactory;

public class SubscriptionStream implements SourceFunction<Subscription> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Logger LOG = Logger.getLogger(SubscriptionStream.class.getName());
	// URL of the JMS server
	// Name of the queue we will receive messages from
	private static String QUEUE = "SUB_EVENT";

	public SubscriptionStream() {

	}

	@Override
	public void cancel() {

	}

	@Override
	public void run(SourceContext<Subscription> context) throws Exception {


		Connection connection=JMSFactory.getJMSConnection();
		connection.start();
		// Creating session for seding messages
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// Getting the queue 'TESTQUEUE'
		Destination destination = session.createQueue(QUEUE);
		// MessageConsumer is used for receiving (consuming) messages
		MessageConsumer consumer = session.createConsumer(destination);
		System.out.println("SubscriptionStream Counter...");
		Thread.sleep(200);
		while (true) {
			Subscription fence = new Subscription();
			javax.jms.Message message = consumer.receive();
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				try {
					LOG.log(Level.INFO, "Received message '" + textMessage.getText() + "'");

					Object document = Configuration.defaultConfiguration().jsonProvider()
							.parse(textMessage.getText().toString());

					fence = GeoUtility.getSubscription(document);
					LOG.log(Level.INFO, "Received fence '" + fence + "'");
					LOG.log(Level.INFO, "Received fence message Time '" + JsonPath.read(document, "$.startTime") + "'");
				} catch (Exception e) {
					LOG.log(Level.SEVERE, e.getMessage(), e);
				}
			}
			/*
			 * Instant instant = Instant.now(); long timeStampMillis =
			 * instant.toEpochMilli();
			 */
			context.collectWithTimestamp(fence, Instant.now().toEpochMilli());
			Thread.sleep(200);
		}
	}
}