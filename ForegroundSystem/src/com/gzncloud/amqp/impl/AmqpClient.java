package com.gzncloud.amqp.impl;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Service
public class AmqpClient {
	private Channel channel;
	private String exchange;

	/**
	 * 
	 * @param deviceInfo
	 * @param locksInfo
	 */
	public void openLockers(String deviceInfo, String locksInfo) {
		try {
			channel.basicPublish(exchange, deviceInfo,
					new AMQP.BasicProperties.Builder().contentType("text/plain").deliveryMode(2).build(),
					locksInfo.getBytes());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public AmqpClient() {
		super();
		// TODO 自动生成的构造函数存根
		Properties props = new Properties();
		try {
			props.load(this.getClass().getResourceAsStream("amqp.properties"));
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(props.getProperty("host"));
			factory.setPort(Integer.parseInt(props.getProperty("port")));
			factory.setUsername(props.getProperty("username"));
			factory.setPassword(props.getProperty("password"));
			exchange = props.getProperty("exchange");
			Connection connection = factory.newConnection();
			channel = connection.createChannel();
		} catch (NumberFormatException | IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection(Connection connection) {
		try {
			channel.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
