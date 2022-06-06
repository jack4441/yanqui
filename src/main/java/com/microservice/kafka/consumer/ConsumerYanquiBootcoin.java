package com.microservice.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.kafka.Util;
import com.microservice.kafka.entity.MessageTransactWallet;
import com.microservice.kafka.entity.RequestTransactWallet;
import com.microservice.kafka.repository.MobileWalletRepository;
import com.microservice.kafka.service.IServiceMobileWallet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component 
public class ConsumerYanquiBootcoin {
	
	@Autowired
	MobileWalletRepository mobileWalletRepository;
	@Autowired
	IServiceMobileWallet serviceMobileWallet;
	@KafkaListener(topics={"transferYanquiBootcoin"}, groupId="spring-boot-kafka")
	public void consume(String message) throws JsonProcessingException {
		MessageTransactWallet messageResult = Util.objectMapper.readValue(message, MessageTransactWallet.class);
		if(messageResult != null)
		{
			RequestTransactWallet request = new RequestTransactWallet();
			mobileWalletRepository.findByMovil(messageResult.getIddetail()).doOnSuccess(wallet-> {
				messageResult.setIddetail(wallet.getIddetail());
				request.setId(wallet.getId());
				request.setDoc_id(wallet.getDoc_id());
				request.setMovil(wallet.getMovil());
				request.setImei(wallet.getImei());
				request.setEmail(wallet.getEmail());
				request.setCash(wallet.getCash());
				request.setType("TCD");
				request.setMessageTransactWallet(messageResult);
			}).block();
			if(request!=null)
				serviceMobileWallet.processTransferMobileWalletYanquiBootcoin(request);
		}
		else
		{
		log.info("El consumidor transferYanquiBootcoin ha recibido un mensaje nulo");
		}
	}
}
