package com.studentUnitTests.syudentest.entity;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testStudentGettersAndSetters() {
        // Create a Student object using no-args constructor
        Student student = new Student();
        student.setId(1L);
        student.setName("Rahul");
        student.setEmailId("rahul@example.com");
        student.setAge(25);

        // Verify getters
        assertEquals(1L, student.getId());
        assertEquals("Rahul", student.getName());
        assertEquals("rahul@example.com", student.getEmailId());
        assertEquals(25, student.getAge());
    }

    @Test
    void testStudentAllArgsConstructor() {
        Student student = new Student(2L, "Vijay", "vijay@example.com", 26);

        assertEquals(2L, student.getId());
        assertEquals("Vijay", student.getName());
        assertEquals("vijay@example.com", student.getEmailId());
        assertEquals(26, student.getAge());
    }

    @Test
    void testStudentBuilder() {
        Student student = Student.builder()
                .id(3L)
                .name("Aman")
                .emailId("aman@example.com")
                .age(24)
                .build();

        assertEquals(3L, student.getId());
        assertEquals("Aman", student.getName());
        assertEquals("aman@example.com", student.getEmailId());
        assertEquals(24, student.getAge());
    }

    @Test
    void testToStringAndEquals() {
        Student s1 = new Student(1L, "Test", "test@example.com", 20);
        Student s2 = new Student(1L, "Test", "test@example.com", 20);

        // Equals and hashCode
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());

        // toString
        assertTrue(s1.toString().contains("Test"));
    }
}
