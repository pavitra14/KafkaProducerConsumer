package com.controller.consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleConsumer {
	private KafkaConsumer<String, String> consumer;
	private ConsumerConfiguration cc;
	
	public SimpleConsumer(ConsumerConfiguration cc)
	{
		this.cc = cc;
		this.consumer = new KafkaConsumer<String, String>(this.cc);
	}
	
	public KafkaConsumer<String, String> getConsumer()
	{
		return this.consumer;
	}
}
