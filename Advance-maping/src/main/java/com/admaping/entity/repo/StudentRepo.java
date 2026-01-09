package com.admaping.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admaping.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

}
