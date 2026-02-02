package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.product.coupon.dto.CouponDto;
import com.product.entity.Product;
import com.product.entity.repo.ProductRepo;
import com.product.payment.dto.PaymentDto;
import com.product.transdto.TransactionalDto;

@RestController 
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepo prepo;
	
	@Autowired
	private RestTemplate template;
	
	
	@PostMapping("/save/{couponCode}")
	public Product saveProduct(@RequestBody TransactionalDto transaction, @PathVariable("couponCode") String cCode) {
		
		CouponDto coupon  = template.getForObject("http://localhost:9090/coupon/get/" + cCode, CouponDto.class);
		
		
		PaymentDto payment = template.postForObject("http://localhost:9191/payment/save",transaction, PaymentDto.class);
		
		transaction.getProduct().setPrice( transaction.getProduct().getPrice() - coupon.getDiscount() );
		
		return prepo.save(transaction.getProduct());
		
	}

}
