package com.microservice.kafka.service;

import com.microservice.kafka.entity.MobileWallet;
import com.microservice.kafka.entity.MobileWalletRedis;
import com.microservice.kafka.entity.RequestMobileWallet;
import com.microservice.kafka.entity.RequestTransactWallet;
import com.microservice.kafka.entity.ResponseDelete;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IServiceMobileWallet {
	
	Flux<Iterable<MobileWalletRedis>> getAllMobileWalletRedis();
	void processTransferMobileWalletYanquiBootcoin(RequestTransactWallet mobileWallet);
	Mono<MobileWallet> processTransferMobileWallet(RequestTransactWallet mobileWallet);
	Mono<MobileWallet> saveMobileWallet(RequestMobileWallet mobileWallet);
	Mono<ResponseDelete> deleteHistoryMobileWallet(String id);

}
