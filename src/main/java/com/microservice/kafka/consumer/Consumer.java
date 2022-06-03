package com.microservice.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.kafka.Util;
import com.microservice.kafka.entity.MessageTransactWallet;
import com.microservice.kafka.repository.StatusDataEventRepository;

@Component
public class Consumer {
	@Autowired
	StatusDataEventRepository dataEventdao;
	@KafkaListener(topics={"transferWallet"}, groupId="spring-boot-kafka")
	public void consume(String message) throws JsonProcessingException {
		MessageTransactWallet messageResult = Util.objectMapper.readValue(message, MessageTransactWallet.class);
		if(messageResult.getStatus().equals("Success"))
		{
			dataEventdao.deleteById(messageResult.getId());
		}
	}
}
