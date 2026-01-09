package com.admaping.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admaping.entity.Laptop;

@Repository
public interface LaptopRepo extends JpaRepository<Laptop, Integer> {

}
