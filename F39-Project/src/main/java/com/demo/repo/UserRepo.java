package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.CustomUser;

@Repository
public interface UserRepo extends JpaRepository<CustomUser, Integer> {

	CustomUser findByEmail(String email);

}
