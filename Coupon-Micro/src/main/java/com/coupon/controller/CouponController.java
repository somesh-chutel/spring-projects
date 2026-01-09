package com.coupon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupon.entity.Coupon;
import com.coupon.entity.repo.CouponRepo;

@RestController
@RequestMapping("/coupon")
public class CouponController {
	
	@Autowired
	private CouponRepo crepo;
	
	@PostMapping("/saveall")
	public List<Coupon> saveAllCoupon(@RequestBody List<Coupon> coupon) {
			
			return crepo.saveAll(coupon);
	}
	
	@GetMapping("/get/{code}")
	public Coupon getCoupon(@PathVariable("code") String code) {
		
		return crepo.findByCouponCode(code);
	}
}
