package com.demo.controller;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Payment;
import com.demo.entity.repo.PaymentRepo;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	
	@Autowired 
	private PaymentRepo prepo; 
	
	@PostMapping("/save")
	public Payment addNewPayment() {
		
		Payment payment = new Payment();
		
		UUID uid = UUID.randomUUID();
		
		payment.setTransactionId(uid.toString());
		payment.setStatus(new Random().nextBoolean() ? "Success" : "Failed" );
		
		return prepo.save(payment);
		
	}
}
