package com.product.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CouponDto {
	
	private int couponId;
	private String couponCode;
	private int discount;
}
