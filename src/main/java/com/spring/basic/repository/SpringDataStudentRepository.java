package com.spring.basic.repository;

import com.spring.basic.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataStudentRepository extends JpaRepository<Student, Long> {
}
