package com.spring.basicjpa.repository;

import com.spring.basicjpa.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
