package com.demo.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Subject;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Integer> {

}
