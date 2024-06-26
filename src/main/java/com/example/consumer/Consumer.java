package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Consumer {
	
	@Autowired
	private PubSubTemplate pubSubTemplate;
	
	@Value("${pubsub.subscription}")
	private String subscriptionName;

	@PostConstruct
	public void consumeMessage() {
		this.pubSubTemplate.subscribe(subscriptionName, message -> {
			
			log.info(String.format("Consumindo mensagem : %s", message.toString()));
			
			message.ack();
		});
	}
	
}
