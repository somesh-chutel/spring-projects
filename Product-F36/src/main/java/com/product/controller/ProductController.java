package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.product.dto.Payment;
import com.product.entity.Product;
import com.product.entity.repo.ProductRepo;
import com.product.po.dto.TransactionalData;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepo prepo;
	
	@Autowired
	private RestTemplate template;
	
	@PostMapping("/save")
	public Product saveProduct(@RequestBody TransactionalData tansaction) {
		
		Product product = tansaction.getProduct();
		Payment payment = tansaction.getPayment();
		
		payment = template.postForObject("http://localhost:8080/payment/save", payment, Payment.class);
		
		return prepo.save(product);
		
	}

}
