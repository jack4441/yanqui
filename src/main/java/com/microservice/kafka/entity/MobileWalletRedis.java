package com.microservice.kafka.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RedisHash("MobileWalletRedis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MobileWalletRedis implements Serializable {
private static final long serialVersionUID = 6570272105397342980L;
private String id;
private String doc_id;
private String movil;
private String imei;
private String email;
private BigDecimal cash;
private String iddetail;
}
