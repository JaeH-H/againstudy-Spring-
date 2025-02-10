package com.spring.basicjpa;

import com.spring.basicjpa.domain.Course;
import com.spring.basicjpa.domain.Student;
import com.spring.basicjpa.repository.CourseRepository;
import com.spring.basicjpa.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Course java = Course.createInitCourse("자바");
        Course spring = Course.createInitCourse("스프링");
        Course kotlin = Course.createInitCourse("코틀린");
        Course ktor = Course.createInitCourse("케이토르");
        List<Course> courseList = List.of(java, spring, kotlin, ktor);
        courseRepository.saveAll(courseList);

        Student gygim = Student.createInitStudent("gygim", java);
        Student steve = Student.createInitStudent("Steve", java);
        Student john = Student.createInitStudent("John", java);
        Student isac = Student.createInitStudent("Isac", java);
        List<Student> studentList = List.of(gygim, steve, john, isac);
        studentRepository.saveAll(studentList);
    }
}
