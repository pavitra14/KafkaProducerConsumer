package com.controller.producer;

import org.apache.kafka.common.serialization.Serializer;


import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaJsonSerializer implements Serializer<Object>{
	
	public byte[] serialize(String topic, Object data) {
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsBytes(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return retVal;
	}

}