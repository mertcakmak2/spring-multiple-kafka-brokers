package com.kafkamultiplebrokers.kafka;

import com.kafkamultiplebrokers.config.TopicConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventConsumer {

    @RetryableTopic(attempts = "4", dltStrategy = DltStrategy.FAIL_ON_ERROR, backoff = @Backoff(delay = 2000 ))
    @KafkaListener(topics = TopicConfig.EVENT_TOPIC, groupId = "group-1")
    public void consumeEvent(Event event) {
        log.info("Event Consumed. ID: {}", event.getId());
    }

    @DltHandler
    public void handleDltEvent(
            Event event, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.EXCEPTION_MESSAGE) String exceptionMessage) {
        log.error("Event on dlt topic={}, payload={}, Event ID={}, exception={}", topic, event, event.getId(), exceptionMessage);
    }
}
