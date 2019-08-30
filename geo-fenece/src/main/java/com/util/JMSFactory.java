package com.util;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSFactory {

	static Connection connection;
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	public static Connection getJMSConnection() throws JMSException {
		if (connection == null) {
			synchronized (JMSFactory.class) {
				if (connection == null) {
					ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
					connection = connectionFactory.createConnection();
				}
			}
		}
		return connection;

	}

}
