package com.example.springcache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springcache.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
