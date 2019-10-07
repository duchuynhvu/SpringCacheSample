package com.example.springcache.cache;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.example.springcache.exception.ResourceNotFoundException;
import com.example.springcache.model.Student;

public interface StudentCacheManager {
    Student getStudent(Long id) throws ResourceNotFoundException;
    Page<Student> getStudents(Pageable pageable);
    Student createStudent(Student std);
    Student updateStudent(Student std) throws ResourceNotFoundException;
    ResponseEntity<?> deleteStudent(Long id) throws ResourceNotFoundException;
}
