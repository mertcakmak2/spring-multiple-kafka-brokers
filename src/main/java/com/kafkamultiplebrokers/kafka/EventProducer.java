package com.kafkamultiplebrokers.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EventProducer {

    private final KafkaTemplate<String, Event> kafkaTemplate;

    public EventProducer(KafkaTemplate<String, Event> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(String topic, Event event) {
        kafkaTemplate.send(topic, UUID.randomUUID().toString(), event);
    }
}
