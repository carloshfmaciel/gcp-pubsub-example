package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.spring.pubsub.PubSubAdmin;
import com.google.pubsub.v1.Subscription;

@Configuration
public class PubSubSubscriptionConfig {

	@Value("${spring.cloud.gcp.pubsub.project-id}")
	private String projectId;

	@Value("${pubsub.topic}")
	private String topicName;

	@Value("${pubsub.subscription}")
	private String subscriptionName;

	@Bean
	Subscription mySubscription(PubSubAdmin pubSubAdmin) {
		Subscription subscription = pubSubAdmin.getSubscription(subscriptionName);
		if (subscription == null) {
			return pubSubAdmin.createSubscription(subscriptionName, topicName);
		}

		return subscription;
	}
}
