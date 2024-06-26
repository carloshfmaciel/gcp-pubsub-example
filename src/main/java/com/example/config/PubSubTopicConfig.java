package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.pubsub.v1.Topic;
import com.google.pubsub.v1.TopicName;

@Configuration
public class PubSubTopicConfig {

	@Value("${spring.cloud.gcp.pubsub.project-id}")
	private String projectId;

	@Value("${pubsub.topic}")
	private String topicName;

	@Bean
	Topic myTopic(TopicAdminClient topicAdminClient) {
		try {
			return topicAdminClient.getTopic(TopicName.of(projectId, topicName));
		} catch (Exception e) {
			return topicAdminClient.createTopic(TopicName.of(projectId, topicName));
		}
	}
}
