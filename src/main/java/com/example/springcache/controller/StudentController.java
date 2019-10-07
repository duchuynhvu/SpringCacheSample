package com.example.springcache.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcache.cache.StudentCacheManager;
import com.example.springcache.exception.ResourceNotFoundException;
import com.example.springcache.model.Lecturer;
import com.example.springcache.model.Student;
import com.example.springcache.model.StudentCache;
import com.example.springcache.service.StudentService;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentCacheManager studentCacheManager;

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student std) {
        return studentService.createStudent(std);
    }
    
    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Long id)
            throws ResourceNotFoundException {
        return studentService.getStudent(id);
    }
    
    @GetMapping("/students")
    public Page<Student> getStudents(Pageable pageable) {
        return studentService.getStudents(pageable);
    }

    @PutMapping("/students")
    public Student updateStudent(@Valid @RequestBody Student std) 
            throws ResourceNotFoundException {
        return studentService.updateStudent(std);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) 
            throws ResourceNotFoundException {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/{id}/lecturers")
    public Set<Lecturer> getLecturers(@PathVariable Long id) throws ResourceNotFoundException {
        return studentService.getLecturers(id);
    }
    
    //=========================================
    // CACHE
    //=========================================
   
    @PostMapping("/cache/students")
    public Student createStudentWithCache(@Valid @RequestBody Student std) {
        return studentCacheManager.createStudent(std);
    }
    @GetMapping("/cache/students/{id}")
    public Student getStudentWithCache(@PathVariable Long id)
            throws ResourceNotFoundException {
        return studentCacheManager.getStudent(id);
    }
    @GetMapping("/cache/students")
    public Page<Student> getStudentsWithCache(Pageable pageable) {
        return studentCacheManager.getStudents(pageable);
    }
    @PutMapping("/cache/students")
    public Student updateStudentWithCache(@Valid @RequestBody Student std) 
            throws ResourceNotFoundException {
        return studentCacheManager.updateStudent(std);
    }
    @DeleteMapping("/cache/students/{id}")
    public ResponseEntity<?> deleteStudentWithCache(@PathVariable Long id) 
            throws ResourceNotFoundException {
        return studentCacheManager.deleteStudent(id);
    }
}
