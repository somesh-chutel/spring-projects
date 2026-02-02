package com.product.payment.dto;

import lombok.Data;

@Data
public class PaymentDto {
	
	private int paymentId;
	private String transactionId;
	private String status;
}
