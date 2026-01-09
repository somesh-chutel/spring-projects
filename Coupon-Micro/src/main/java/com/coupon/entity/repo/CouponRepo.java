package com.coupon.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coupon.entity.Coupon;
import java.util.List;


public interface CouponRepo extends JpaRepository<Coupon, Integer> {
	
	public Coupon findByCouponCode(String couponCode);
}
