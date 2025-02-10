package com.spring.basicjpa.repository;

import com.spring.basicjpa.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
