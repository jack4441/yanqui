package com.microservice.kafka.producer;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Producer {
private final KafkaTemplate<Integer, String> kafkaTemplate;
public void publisMessage(String message) {
	Mono.just(kafkaTemplate.send("client", 45, message));
}

}
