package com.microservice.kafka.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.microservice.kafka.entity.MobileWallet;

public interface MobileWalletRepository extends ReactiveMongoRepository<MobileWallet, String> {

}
