package com.product.po.dto;

import com.product.dto.Payment;
import com.product.entity.Product;

import lombok.Data;

@Data
public class TransactionalData {
	
	private Product product;
	private Payment payment;

}
