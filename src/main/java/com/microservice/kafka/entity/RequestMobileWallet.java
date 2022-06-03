package com.microservice.kafka.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class RequestMobileWallet implements Serializable{

private static final long serialVersionUID = -904074258446774432L;
private MobileWallet mobileWallet;

public MobileWallet toMobileWallet()
{
	return MobileWallet.builder()
			.id(mobileWallet.getId())
			.doc_id(mobileWallet.getDoc_id())
			.movil(mobileWallet.getMovil())
			.imei(mobileWallet.getImei())
			.email(mobileWallet.getEmail())
			.cash(mobileWallet.getCash())
			.iddetail(mobileWallet.getIddetail())
			.build();
}

}
