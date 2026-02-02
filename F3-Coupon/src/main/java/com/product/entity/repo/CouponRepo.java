package com.product.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.entity.Coupon;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Integer> {
	
	public Coupon findByCouponCodeAllIgnoringCase(String couponCode);
}
