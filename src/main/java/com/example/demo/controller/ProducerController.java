package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		String status = "OK";
		try {
			producer.sendMessage(mex);
		} catch (Exception e) {
			status="KO";
		}
		return status;
	}
	
}
