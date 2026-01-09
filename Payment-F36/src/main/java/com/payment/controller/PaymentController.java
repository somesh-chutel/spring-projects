package com.payment.controller;

import java.util.Random;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.enity.Payment;
import com.payment.enity.repo.PaymentRepo;

@RestController 
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentRepo payrepo;
	
	@PostMapping("/save")
	public Payment savepayment(@RequestBody Payment payment) {
			
	    Random random = new Random();
	    UUID uuid = UUID.randomUUID();
		payment.setStatus(random.nextBoolean() ? "Success": "Failed");
		payment.setTransactionId(uuid.toString());
		
		return payrepo.save(payment);
		
	}
}
