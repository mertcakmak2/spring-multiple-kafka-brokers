package com.kafkamultiplebrokers.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class TopicConfig {

    public static final String EVENT_TOPIC = "event-topic";

    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name(EVENT_TOPIC)
                .replicas(3)
                .partitions(9)
                .build();
    }

}
