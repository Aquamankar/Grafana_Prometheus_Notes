package com.studentUnitTests.syudentest.repository;



import com.studentUnitTests.syudentest.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    @BeforeEach
    public void setup() {
        studentRepository.deleteAll();
    }


    @Test
    @DisplayName("Test saving a student")
    void testSaveStudent() {
        Student student = Student.builder()
                .name("Rahul")
                .emailId("rahul@gmail.com")
                .age(23)
                .build();

        Student savedStudent = studentRepository.save(student);

        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isGreaterThan(0);
        assertThat(savedStudent.getName()).isEqualTo("Rahul");
    }

    @Test
    @DisplayName("Test finding student by ID")
    void testFindById() {
        Student student = Student.builder()
                .name("Vijay")
                .emailId("vijay@gmail.com")
                .age(25)
                .build();

        studentRepository.save(student);
        Optional<Student> found = studentRepository.findById(student.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Vijay");
    }

    @Test
    @DisplayName("Test finding all students")
    void testFindAllStudents() {
        studentRepository.save(Student.builder().name("A").emailId("a@gmail.com").age(20).build());
        studentRepository.save(Student.builder().name("B").emailId("b@gmail.com").age(21).build());

        List<Student> students = studentRepository.findAll();

        assertThat(students).isNotEmpty();
        assertThat(students.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test deleting a student")
    void testDeleteStudent() {
        Student student = Student.builder()
                .name("DeleteMe")
                .emailId("delete@gmail.com")
                .age(30)
                .build();

        studentRepository.save(student);
        studentRepository.delete(student);

        Optional<Student> deleted = studentRepository.findById(student.getId());
        assertThat(deleted).isEmpty();
    }
}
