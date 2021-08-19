package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TestDTO;

@Service
public class KafkaProducer {
	
	@Autowired @Qualifier("kafkaTemplateJson")
	KafkaTemplate<String, byte[]> kafkaTemplate;
	
	public void sendMessage(String mex) {
		
		TestDTO test = new TestDTO(mex);
		Message<TestDTO> kMessage = MessageBuilder.withPayload(test)
				.setHeader(KafkaHeaders.TOPIC, "my_topic")
				.setHeader(KafkaHeaders.MESSAGE_KEY, UUID.randomUUID().toString())
				.build();
		
		kafkaTemplate.send(kMessage);
		System.out.println("messaggio inviato");
	}
	
	
}
