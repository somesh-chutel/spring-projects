package com.demo.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Professor;

@Repository
public interface ProfessorRepo extends JpaRepository<Professor, Integer> {

}
