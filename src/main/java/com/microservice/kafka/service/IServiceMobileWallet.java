package com.microservice.kafka.service;

import com.microservice.kafka.entity.MobileWallet;
import com.microservice.kafka.entity.RequestMobileWallet;
import com.microservice.kafka.entity.RequestTransactWallet;

import reactor.core.publisher.Mono;

public interface IServiceMobileWallet {

	Mono<MobileWallet> processTransferMobileWallet(RequestTransactWallet mobileWallet);
	Mono<MobileWallet> saveMobileWallet(RequestMobileWallet mobileWallet);

}
