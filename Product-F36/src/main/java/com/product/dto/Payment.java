package com.product.dto;

import lombok.Data;

@Data
public class Payment {
	
	private int paymentId;
	private String status;
	private String transactionId;
}
