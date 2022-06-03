package com.microservice.kafka.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("mobile_wallet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MobileWallet implements Serializable {

private static final long serialVersionUID = 8001428519817872016L;
@Id
private String id;
@Field
private String doc_id;
@Field
private String movil;
@Field
private String imei;
@Field
private String email;
@Field
private double cash;
//id del producto Tarjeta de débito asociada.
@Field
private String iddetail;

public MobileWallet withDrawals(double amount)
{
	this.setCash(this.cash-amount);
	return this;
}

public MobileWalletRedis toMobileWalletRedis() {
	return MobileWalletRedis.builder()
			.id(this.id)
			.doc_id(this.doc_id)
			.movil(this.movil)
			.imei(this.imei)
			.email(this.email)
			.cash(new BigDecimal(this.cash))
			.iddetail(this.iddetail)
			.build();
}

}
