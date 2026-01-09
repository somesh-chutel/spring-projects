package com.payment.enity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.enity.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {

}
