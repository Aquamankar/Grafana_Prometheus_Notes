package com.studentUnitTests.syudentest.integration;

import com.studentUnitTests.syudentest.entity.Student;
import com.studentUnitTests.syudentest.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@Testcontainers
public class StudentRepositoryTC extends  AbstractBasedContainerClass {
//    @Container
//    private  static MySQLContainer<?> mysqlContainer=new MySQLContainer<>("mysql:8.0.36");
//
//    @DynamicPropertySource
//    static void  dynamicResouse(DynamicPropertyRegistry registry){
//        registry.add("spring.datasource.url",
//                mysqlContainer::getJdbcUrl);
//        registry.add("spring.datasource.username",mysqlContainer::getUsername);
//
//        registry.add("spring.datasource.password",mysqlContainer::getPassword);
//    }
    @Autowired
    StudentRepository repository;
    @BeforeEach
    void setUp(){
        repository.deleteAll();
    }


    @Test
    public void givenStudentId_whenfindById_thenOptonalOfStudent(){

    //given

        Student student = new Student();
        student.setName("rahul");
        student.setEmailId("new@gmail.com");
        student.setAge(23);
        Student save = repository.save(student);
        //when

        Optional<Student> byId = repository.findById(save.getId());
        //then
        assertTrue(byId.isPresent());
        assertNotNull(byId.get());
        Assertions.assertEquals(byId.get().getId(),save.getId());


    }

    @Test
    public void givenStudents_whenfindAll_thenListStudents(){
    //given
        Student student = new Student();
        student.setName("rahul");
        student.setEmailId("new@gmail.com");
        student.setAge(23);

        Student student1 = new Student();
        student1.setName("vijay");
        student1.setEmailId("Oldnew@gmail.com");
        student1.setAge(26);

        List<Student> studentList= Arrays.asList(student1,student);
        repository.saveAll(studentList);
    //when

        List<Student> all = repository.findAll();
        //then
        Assertions.assertEquals(all.size(),2);

    }

    @Test
    public void givenStudent_whenSave_thenReturnSavedStudent() {
        // given
        Student student = new Student();
        student.setName("Rahul");
        student.setEmailId("rahul@example.com");
        student.setAge(23);

        // when
        Student saved = repository.save(student);

        // then
        assertNotNull(saved.getId(), "Saved student ID should not be null");
        assertEquals("Rahul", saved.getName());
        assertEquals("rahul@example.com", saved.getEmailId());
        assertEquals(23, saved.getAge());
    }

    @Test
    public void givenStudentId_whenDelete_thenStudentShouldBeRemoved() {
        // given
        Student student = new Student();
        student.setName("Vijay");
        student.setEmailId("vijay@example.com");
        student.setAge(26);
        Student saved = repository.save(student);

        Long savedId = saved.getId();
        assertTrue(repository.findById(savedId).isPresent(), "Student should exist before delete");

        // when
        repository.deleteById(savedId);

        // then
        Optional<Student> deletedStudent = repository.findById(savedId);
        assertFalse(deletedStudent.isPresent(), "Student should be deleted");
    }
}
