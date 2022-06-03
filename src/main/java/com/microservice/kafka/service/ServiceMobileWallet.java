package com.microservice.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.kafka.entity.MobileWallet;
import com.microservice.kafka.entity.RequestMobileWallet;
import com.microservice.kafka.entity.RequestTransactWallet;
import com.microservice.kafka.producer.Producer;
import com.microservice.kafka.repository.HistoryMovilWalletRepository;
import com.microservice.kafka.repository.MobileWalletRepository;
import com.microservice.kafka.repository.StatusDataEventRepository;

import reactor.core.publisher.Mono;

@Service
public class ServiceMobileWallet implements IServiceMobileWallet {

	@Autowired
	MobileWalletRepository mobileWalletdao;
	@Autowired
	HistoryMovilWalletRepository historyMovilWalletdao;
	@Autowired
	StatusDataEventRepository statusDataEventdao;
	@Autowired
	Producer producer;
	
	@Override
	public Mono<MobileWallet> processTransferMobileWallet(RequestTransactWallet mobileWallet) {
		// TODO Auto-generated method stub
		//Si es un retiro
		if(mobileWallet.getType().equals("R"))
			return mobileWalletdao.findById(mobileWallet.toMessageTransactWallet().getId())
					.map(value-> value.withDrawals(mobileWallet.toMessageTransactWallet().getAmount()))
					.flatMap(mobileWalletdao::save);
		//Si es una transacción en donde el que transfiere y el destinatario poseen una tarjeta de debito asociado.
		if(mobileWallet.getType().equals("TCD")&&mobileWallet.toMessageTransactWallet().getNumber_destination().isEmpty())
			return mobileWalletdao.findById(mobileWallet.getId())
					.map(value-> value.withDrawals(mobileWallet.toMessageTransactWallet().getAmount()))
					.flatMap(mobileWalletdao::save)
					.doOnSuccess(value->{
						var resultsavehistory = historyMovilWalletdao.save(value.toMobileWalletRedis());
						if(resultsavehistory!=null)
						{
							producer.publisMessage(mobileWallet.toMessageTransactWallet().toString());
							statusDataEventdao.save(mobileWallet.toMessageTransactWallet().toStatusDataEventRedis());
						}
					});
		else
			return Mono.just(MobileWallet.builder().build());
	}

	@Override
	public Mono<MobileWallet> saveMobileWallet(RequestMobileWallet mobileWallet) {
		// TODO Auto-generated method stub
		if(mobileWallet.toMobileWallet().getId()==null)
			return mobileWalletdao.save(mobileWallet.toMobileWallet()).doOnSuccess(value->{
			});
		else
			return Mono.just(MobileWallet.builder().build());
	}

}
