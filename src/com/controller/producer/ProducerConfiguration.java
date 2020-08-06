package com.controller.producer;

import java.util.Properties;

public class ProducerConfiguration extends Properties {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String BOOTSTRAP_SERVER = "localhost:9092";
	public String ACKNOWLEDGEMENT = "all";
	public int RETRIES = 0;
	public int BATCH_SIZE = 16384;
	public int LINGER_MS = 1;
	public int BUFFER_MEMORY = 33554432;
	public String KEY_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
	public String VALUE_SERIALIZER = "com.controller.producer.KafkaJsonSerializer";
	
	// Constructor
	public ProducerConfiguration() {
		// Bootstrap server details		
		this.put("bootstrap.servers", this.BOOTSTRAP_SERVER);
		
		//Set acknowledgements for producer requests. 
		this.put("acks",this.ACKNOWLEDGEMENT);
		
		//If the request fails, the producer can automatically retry
		this.put("retries",this.RETRIES);
		
		//Specify buffer size in config
		this.put("batch.size", this.BATCH_SIZE);
		
		//Reduce the no of requests less than 0
		this.put("linger.ms",this.LINGER_MS);
		
		//The buffer.memory controls the total amount of memory available to the producer for buffering.
		this.put("buffer.memory", this.BUFFER_MEMORY);
		
		// Key Serializer	
		this.put("key.serializer", this.KEY_SERIALIZER);
		
		// Value Serializer
		this.put("value.serializer", this.VALUE_SERIALIZER);
	}
	
	public Properties update()
	{
		// Bootstrap server details		
		this.replace("bootstrap.servers", this.BOOTSTRAP_SERVER);
				
		//Set acknowledgements for producer requests. 
		this.replace("acks",this.ACKNOWLEDGEMENT);
				
		//If the request fails, the producer can automatically retry
		this.replace("retries",this.RETRIES);
				
		//Specify buffer size in config
		this.replace("batch.size", this.BATCH_SIZE);
				
		//Reduce the no of requests less than 0
		this.replace("linger.ms",this.LINGER_MS);
				
		//The buffer.memory controls the total amount of memory available to the producer for buffering.
		this.replace("buffer.memory", this.BUFFER_MEMORY);
				
		// Key Serializer	
		this.replace("key.serializer", this.KEY_SERIALIZER);
				
		// Value Serializer
		this.replace("value.serializer", this.VALUE_SERIALIZER);
		
		return this;
	}
	
}
