package com.example.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Producer {

	@Autowired
	private PubSubTemplate pubSubTemplate;
	
	@Value("${pubsub.topic}")
	private String topicName;
	
	public void publishMessage(String message) {
		log.info(String.format("Produzindo mensagem: %s", message));
		this.pubSubTemplate.publish(topicName, message);
	}
	
}
