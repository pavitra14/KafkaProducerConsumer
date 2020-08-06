package com.controller.producer;

//import simple producer packages
import org.apache.kafka.clients.producer.Producer;
//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;



public class SimpleProducer {
	private Producer<String, CustomObject> producer;
	private ProducerConfiguration pc;
	
	public SimpleProducer(ProducerConfiguration pc)
	{
		this.pc = pc;
		this.producer = new KafkaProducer<String, CustomObject>(this.pc);
	}
	
	public Producer<String,CustomObject> getProducer() {
		return this.producer;
	}
}
