package com.example.springcache.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springcache.exception.ResourceNotFoundException;
import com.example.springcache.model.Lecturer;
import com.example.springcache.model.Student;
import com.example.springcache.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepo;

    public Student createStudent(Student std) {
        return studentRepo.save(std);// Saves and return the new student
    }

    public Student getStudent(Long id) throws ResourceNotFoundException {
        // If the record exists by id return it, otherwise throw an exception
        return studentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student", id));
    }

    public Page<Student> getStudents(Pageable pageable) {
        return studentRepo.findAll(pageable);
    }

    public Student updateStudent(Student student) 
            throws ResourceNotFoundException {
        // Finds student by id, maps it's content, updates new values and save.
        return studentRepo.findById(student.getId()).map(toUpdate -> {
            toUpdate.setFirstName(student.getFirstName());
            toUpdate.setLastName(student.getLastName());
            toUpdate.setGpa(student.getGpa());
            return studentRepo.save(toUpdate);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Student not found :: " + student.getId()));
    }

    public ResponseEntity<?> deleteStudent(Long id) 
            throws ResourceNotFoundException {
        // If id exists, delete the record and return a response message
        return studentRepo.findById(id).map(toDelete -> {
            studentRepo.delete(toDelete);
            return ResponseEntity.ok("Student id " + id + " deleted");
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Student not found :: " + id));
    }

    public Set<Lecturer> getLecturers(Long id) throws ResourceNotFoundException {
        // Finds student by id and returns it's recorded lecturers
        return studentRepo.findById(id).map(std -> {
            return std.getLecturers();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Student not found :: " + id));
    }
}
