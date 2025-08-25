package com.studentUnitTests.syudentest.controller;

import com.studentUnitTests.syudentest.entity.Student;
import com.studentUnitTests.syudentest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("api/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student save = studentRepository.save(student);
        return new ResponseEntity<>(save, HttpStatus.CREATED);

    }

    //6. Write Integration Test for getAllStudents REST API with local MySQL Database
    //Add Testcontainers to Spring Boot Application ( maven dependencies)
    //
    //8. Write Integration Test getAllStudents REST API using Testcontainers
    //
    //9. Write Integration Tests for StudentRepository
    //
    //10. Write Integration Test StudentRepository using Testcontainers
    @GetMapping("api/all")
    public List<Student> getAllStudent(){
        List<Student> studentList = studentRepository.findAll();
        return studentList;

    }
}
