package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.Coupon;
import com.product.entity.repo.CouponRepo;

@RestController 
@RequestMapping("/coupon")
public class CouponController {
	
	@Autowired
	private CouponRepo crepo;
	
	@PostMapping("/saveall")
	public List<Coupon> saveAllCoupon(@RequestBody List<Coupon> coupon){
			
		return crepo.saveAll(coupon);
	}
	
	@GetMapping("/get/{couponCode}")
	public Coupon getCoupon(@PathVariable("couponCode") String couponC) {
		
		return crepo.findByCouponCodeAllIgnoringCase(couponC);
	}
}	
