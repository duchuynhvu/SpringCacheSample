package com.example.springcache.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springcache.exception.ResourceNotFoundException;
import com.example.springcache.model.Lecturer;
import com.example.springcache.model.Student;
import com.example.springcache.model.StudentCache;

@Service
public interface StudentService {
    Student createStudent(Student std);
    Student getStudent(Long id) throws ResourceNotFoundException;
    Page<Student> getStudents(Pageable pageable);
    Student updateStudent(Student std) throws ResourceNotFoundException;
    ResponseEntity<?> deleteStudent(Long id) throws ResourceNotFoundException;
    Set<Lecturer> getLecturers(Long id) throws ResourceNotFoundException;
}
