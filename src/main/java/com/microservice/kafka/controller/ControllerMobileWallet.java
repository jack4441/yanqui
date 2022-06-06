package com.microservice.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.kafka.entity.MobileWallet;
import com.microservice.kafka.entity.MobileWalletRedis;
import com.microservice.kafka.entity.RequestMobileWallet;
import com.microservice.kafka.entity.RequestTransactWallet;
import com.microservice.kafka.entity.ResponseDelete;
import com.microservice.kafka.service.IServiceMobileWallet;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("wallet/v1")
public class ControllerMobileWallet {
@Autowired
IServiceMobileWallet serviceMobileWallet;

@GetMapping(path = "/")
public String get(){
return "running...";
}

@GetMapping(path= "/getwalletredis", produces = MediaType.APPLICATION_JSON_VALUE)
public Flux<Iterable<MobileWalletRedis>> getAllMobileWalletRedis(){
	return serviceMobileWallet.getAllMobileWalletRedis();
}
@PostMapping(path = "/transfer", produces = MediaType.APPLICATION_JSON_VALUE
	, consumes = MediaType.APPLICATION_JSON_VALUE)
public Mono<MobileWallet> processTransfer(@RequestBody RequestTransactWallet request){
	return serviceMobileWallet.processTransferMobileWallet(request);
}
@PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE
, consumes = MediaType.APPLICATION_JSON_VALUE)
public Mono<MobileWallet> save(@RequestBody RequestMobileWallet request){
return serviceMobileWallet.saveMobileWallet(request);
}
@DeleteMapping(path = "/deletehistorymobilewallet/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
public Mono<ResponseDelete> deleteHistoryMobileWallet(@PathVariable String id){
	return serviceMobileWallet.deleteHistoryMobileWallet(id);
}
}
