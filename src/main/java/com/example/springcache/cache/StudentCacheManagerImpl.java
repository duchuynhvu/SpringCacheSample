package com.example.springcache.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.example.springcache.exception.ResourceNotFoundException;
import com.example.springcache.model.Student;
import com.example.springcache.service.StudentService;

@Configuration
public class StudentCacheManagerImpl implements StudentCacheManager {

    @Autowired
    private StudentService studentService;

    @Override
    @Cacheable(value = "student", key = "#id")
    public Student getStudent(Long id) throws ResourceNotFoundException {

        sleep5SecsForCacheTest();

        return studentService.getStudent(id);
    }

    @Override
    @Cacheable(value = "allStudentCache")
    public Page<Student> getStudents(Pageable pageable) {

        sleep5SecsForCacheTest();

        return studentService.getStudents(pageable);
    }

    @Override
    @CacheEvict(value = "allStudentCache", allEntries = true)
    public Student createStudent(Student std) {
        return studentService.createStudent(std);
    }

    @Caching(
            put = {
                    @CachePut(value = "student", key = "#std.id")}, 
            evict = {
                    @CacheEvict(value = "allStudentCache", allEntries= true)}
            )
    public Student updateStudent(Student std) throws ResourceNotFoundException {
        return studentService.updateStudent(std);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "student", key = "#id"),
                    @CacheEvict(value = "allStudentCache", allEntries= true)})
    public ResponseEntity<?> deleteStudent(Long id)
            throws ResourceNotFoundException {
        return studentService.deleteStudent(id);
    }

    private void sleep5SecsForCacheTest() {
        try {
            System.out.println(
                    "Going to sleep for 5 Secs.. to simulate backend call.");
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
