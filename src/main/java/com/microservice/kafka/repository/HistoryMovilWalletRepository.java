package com.microservice.kafka.repository;

import org.springframework.data.repository.CrudRepository;

import com.microservice.kafka.entity.MobileWalletRedis;

public interface HistoryMovilWalletRepository extends CrudRepository<MobileWalletRedis, String> {

}
