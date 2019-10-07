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

import com.example.springcache.exception.ResourceNotFoundException;
import com.example.springcache.model.Lecturer;
import com.example.springcache.model.Student;
import com.example.springcache.repository.LecturerRepository;
import com.example.springcache.repository.StudentRepository;

@RestController
@RequestMapping("/api/v1/lecturers")
public class LecturerController {

    @Autowired
    private LecturerRepository lecturers;

    @Autowired
    private StudentRepository students;

    @PostMapping()
    public Lecturer createLecturer(@Valid @RequestBody Lecturer lecturer) {
        return this.lecturers.save(lecturer);// return the new lecturer
    }

    @GetMapping()
    public Page<Lecturer> getLecturers(Pageable pageable) {
        return lecturers.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Lecturer getLecturer(@PathVariable Long id)
            throws ResourceNotFoundException {

        return lecturers.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Lecturer not found ::" + id));
    }

    @PutMapping()
    public Lecturer updateLecturer(@Valid @RequestBody Lecturer lecturer)
            throws ResourceNotFoundException {
        // Finds lecturer by id,
        // -- maps it's content, update new values and save.
        // -- Throws an exception if not found.
        return lecturers.findById(lecturer.getId()).map(toUpdate -> {
            toUpdate.setFirstName(lecturer.getFirstName());
            toUpdate.setLastName(lecturer.getLastName());
            toUpdate.setSalary(lecturer.getSalary());

            return lecturers.save(toUpdate);

        }).orElseThrow(() -> new ResourceNotFoundException(
                "Lecturer not found :: " + lecturer.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLecturer(@PathVariable Long id)
            throws ResourceNotFoundException {

        return lecturers.findById(id).map(toDelete -> {
            lecturers.delete(toDelete);

            return ResponseEntity.ok("Lecturer id " + id + " deleted");

        }).orElseThrow(() -> new ResourceNotFoundException(
                "Lecturer not found :: " + id));
    }

    @GetMapping("/{lecturerId}/students")
    public Set<Student> getStudents(@PathVariable Long lecturerId)
            throws ResourceNotFoundException {
        // Finds lecturer by id and returns it's recorded students
        return lecturers.findById(lecturerId).map(lecturer -> {
            return lecturer.getStudents();
            
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Lecturer not found :; " + lecturerId));
    }

    @PostMapping("/{id}/students/{studentId}")
    public Set<Student> addStudent(@PathVariable Long id,
            @PathVariable Long studentId) throws ResourceNotFoundException {

        Student student = students.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found :: " + studentId));

        // Finds a lecturer and adds the given student to the lecturer's set.
        return lecturers.findById(id).map(lecturer -> {
            lecturer.getStudents().add(student);

            return lecturers.save(lecturer).getStudents();

        }).orElseThrow(() -> new ResourceNotFoundException(
                "Lecturer not found :: " + id));
    }

}
