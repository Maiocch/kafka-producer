package com.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.KafkaProducer;

@RestController
public class ProducerController {
	
	@Autowired
	KafkaProducer producer;
	
	@GetMapping("/publish/{msg}")
	public String createMessage(@PathVariable("msg") String mex) {

//		ObjectMapper mapper = Jackson2ObjectMapperBuilder.xml().build();
//		ObjectMapper jsonMapper = Jackson2ObjectMapperBuilder.json().build();



		String status = "OK";
		try {
			producer.sendMessage(mex);
		} catch (Exception e) {
			status="KO";
		}
		return status;
	}
	
}
