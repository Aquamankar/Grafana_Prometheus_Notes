package com.studentUnitTests.syudentest.repository;

import com.studentUnitTests.syudentest.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
