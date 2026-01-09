package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.product.ProductMicroApplication;
import com.product.dto.CouponDto;
import com.product.entity.Product;
import com.product.entity.repo.ProductRepo;

@RestController
@RequestMapping("/product")
public class ProductController {

	
	@Autowired
	private ProductRepo prepo;
	
	@Autowired
	private RestTemplate template;

	
	@PostMapping("/saveall")
	public List<Product> saveAllProduct(@RequestBody List<Product> product){
		
		return prepo.saveAll(product);
	}
	
	@PostMapping("/save")
	public Product saveProduct(@RequestBody Product product) {
		
		CouponDto coupon = template.getForObject("http://localhost:8080/coupon/get/" + product.getCouponCode(), CouponDto.class);
		
		product.setPrice(product.getPrice() - coupon.getDiscount());
		
		return prepo.save(product);
	}
}
