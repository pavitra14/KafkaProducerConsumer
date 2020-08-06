package com.controller.consumer;

import java.util.Properties;
public class ConsumerConfiguration extends Properties{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String BOOTSTRAP_SERVER = "localhost:9092";
	public String GROUP_ID = "test";
	public boolean ENABLE_AUTO_COMMIT = true;
	public int AUTO_COMMIT_INTERVAL_MS = 1000;
	public int SESSION_TIMEOUT_MS = 30000;
	public String KEY_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
	public String VALUE_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
	
	// Constructor
	public ConsumerConfiguration()
	{
		this.put("bootstrap.servers", this.BOOTSTRAP_SERVER);
		
		this.put("group.id", this.GROUP_ID);
		
		this.put("enable.auto.commit",String.valueOf(this.ENABLE_AUTO_COMMIT));
		
		this.put("auto.commit.interval.ms",String.valueOf(this.AUTO_COMMIT_INTERVAL_MS));
		
		this.put("session.timeout.ms", String.valueOf(this.SESSION_TIMEOUT_MS));
		
		this.put("key.deserializer", this.KEY_DESERIALIZER);
		
		this.put("value.deserializer", this.VALUE_DESERIALIZER);	
		
	}
	
	public Properties update()
	{
		this.replace("bootstrap.servers", this.BOOTSTRAP_SERVER);

		this.replace("group.id", this.GROUP_ID);

		this.replace("enable.auto.commit",String.valueOf(this.ENABLE_AUTO_COMMIT));

		this.replace("auto.commit.interval.ms",String.valueOf(this.AUTO_COMMIT_INTERVAL_MS));

		this.replace("session.timeout.ms", String.valueOf(this.SESSION_TIMEOUT_MS));

		this.replace("key.deserializer", this.KEY_DESERIALIZER);

		this.replace("value.deserializer", this.VALUE_DESERIALIZER);
		
		return this;
	}
}
