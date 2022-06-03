package com.microservice.kafka.repository;

import org.springframework.data.repository.CrudRepository;

import com.microservice.kafka.entity.StatusDataEventRedis;

public interface StatusDataEventRepository extends CrudRepository<StatusDataEventRedis, String> {

}
