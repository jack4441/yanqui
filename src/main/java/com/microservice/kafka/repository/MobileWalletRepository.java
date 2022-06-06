package com.microservice.kafka.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.microservice.kafka.entity.MobileWallet;

import reactor.core.publisher.Mono;

public interface MobileWalletRepository extends ReactiveMongoRepository<MobileWallet, String> {

	Mono<MobileWallet> findByMovil(String movil);
	
}
