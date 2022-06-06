package com.microservice.kafka.entity;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDelete implements Serializable {
private static final long serialVersionUID = 6291037769867095860L;
private String response;
}
