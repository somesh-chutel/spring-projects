package com.product.transdto;

import com.product.entity.Product;
import com.product.payment.dto.PaymentDto;

import lombok.Data;

@Data
public class TransactionalDto {
	
	private PaymentDto payment;
	private Product product;
}
