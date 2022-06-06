package com.microservice.kafka.entity;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
public class RequestTransactWallet implements Serializable {

private static final long serialVersionUID = 483682611827829119L;
private MessageTransactWallet messageTransactWallet;
private String id;
private String doc_id;
private String movil;
private String imei;
private String email;
private double cash;
private String type;

public MessageTransactWallet toMessageTransactWallet() {
	return MessageTransactWallet.builder()
			.id(UUID.randomUUID().toString())
			.idTransact(UUID.randomUUID().toString())
			.amount(messageTransactWallet.getAmount())
			.iddetail(messageTransactWallet.getIddetail())
			.mobile_buyer("")
			.mobile_seller("")
			.number_destination(messageTransactWallet.getNumber_destination())
			.destination(messageTransactWallet.getDestination())
			.status("Process")
			.build();
}
	
}
