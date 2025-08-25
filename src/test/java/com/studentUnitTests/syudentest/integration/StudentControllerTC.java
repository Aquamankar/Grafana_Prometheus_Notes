package com.studentUnitTests.syudentest.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentUnitTests.syudentest.entity.Student;
import com.studentUnitTests.syudentest.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@Testcontainers
public class StudentControllerTC extends AbstractBasedContainerClass {

//
//    @Container
//    private  static MySQLContainer<?> mysqlContainer=new MySQLContainer<>("mysql:8.0.36");
//
//@DynamicPropertySource
//static void  dynamicResouse(DynamicPropertyRegistry registry){
//    registry.add("spring.datasource.url",
//               mysqlContainer::getJdbcUrl);
//        registry.add("spring.datasource.username",mysqlContainer::getUsername);
//
//        registry.add("spring.datasource.password",mysqlContainer::getPassword);
//}
    @Autowired
    StudentRepository repository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        repository.deleteAll();;
    }

    @Test
    void givenStudentObject_When_createStudent_ThenCreate() throws Exception {
        //given
        Student student = new Student();
        student.setName("rahul");
        student.setEmailId("new@gmail.com");
        student.setAge(23);
        //  repository.save(student);
        //when

        ResultActions resultActions =mockMvc.perform(MockMvcRequestBuilders.post("/api/create").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));

        resultActions.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("rahul"))
                .andExpect(jsonPath("$.emailId").value("new@gmail.com"))
                .andExpect(jsonPath("$.age").value(23));
    }
    @Test
    public void givenno_whengetAllStudent_thenListStudent() throws Exception {
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

        ResultActions actions= mockMvc.perform(MockMvcRequestBuilders.get("/api/all"));
        //then
        actions.andDo(print()).andExpect(jsonPath("$.size()").value(2));
    }
}
