package com.demo.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {

}
