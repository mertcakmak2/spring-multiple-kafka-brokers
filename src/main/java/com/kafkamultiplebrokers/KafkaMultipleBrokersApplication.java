package com.kafkamultiplebrokers;

import com.kafkamultiplebrokers.config.TopicConfig;
import com.kafkamultiplebrokers.kafka.Event;
import com.kafkamultiplebrokers.kafka.EventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.UUID;

@SpringBootApplication
@EnableKafka
@Slf4j
public class KafkaMultipleBrokersApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaMultipleBrokersApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(EventProducer eventProducer) {
        return args -> {
            log.info("Event producing...");
            var event = Event.builder().id(UUID.randomUUID().toString()).content("content").build();
            eventProducer.sendEvent(TopicConfig.EVENT_TOPIC, event);
            log.info("Event produced.");
        };
    }

}
