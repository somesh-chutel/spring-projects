package com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProductMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMicroApplication.class, args);
	}
	
	@Bean
	RestTemplate restTemplate() {
		
		return new RestTemplate();
	}

}
