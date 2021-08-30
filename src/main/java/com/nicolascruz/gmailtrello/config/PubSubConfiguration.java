package com.nicolascruz.gmailtrello.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.pubsub.v1.Topic;
import com.google.pubsub.v1.TopicName;

@Configuration
public class PubSubConfiguration {

	@Bean
	public static void createTopicExample() throws IOException {
		String projectId = "gmailtrello-324204";
		String topicId = "GmailTopic";
	    try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
	      TopicName topicName = TopicName.of(projectId, topicId);
	      Topic topic = topicAdminClient.createTopic(topicName);
	      System.out.println("Created topic: " + topic.getName());
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	  }
}
