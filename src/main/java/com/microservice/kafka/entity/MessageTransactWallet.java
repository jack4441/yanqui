package com.microservice.kafka.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageTransactWallet implements Serializable {

private static final long serialVersionUID = -4528411981453024674L;
private String id;
private String idTransact;
private double amount;
private String iddetail;
private String number_destination;
private String destination;
private String status;

public StatusDataEventRedis toStatusDataEventRedis() {
	return StatusDataEventRedis.builder()
			.id(this.id)
			.idTransact(this.idTransact)
			.typeEvent("TransactWallet")
			.status(this.status)
			.build();
}

@Override
public String toString() {
	// TODO Auto-generated method stub
	return "{\r\n"
			+ "    \"id\": "+this.id+",\r\n"
			+ "    \"iddetail\": "+this.idTransact+",\r\n"
			+ "    \"amount\": "+this.amount+",\r\n"
			+ "    \"iddetail\": "+this.iddetail+",\r\n"
			+ "    \"destination\": "+this.number_destination+",\r\n"
			+ "    \"destination\": "+this.destination+",\r\n"
			+ "    \"status\": "+this.status+"\r\n"
			+ "}";
}

}
