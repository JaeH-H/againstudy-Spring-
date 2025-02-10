package com.spring.basicjpa.domain;

import com.spring.basicjpa.dto.course.CourseCreateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(name = "course")
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Student> students;

    protected Course() {}

    private Course(String name) {
        this.name = name;
    }

    public static Course createFrom(CourseCreateRequestDto courseCreateRequestDto) {
        String courseName = courseCreateRequestDto.getName();
        return new Course(courseName);
    }

    public static Course createInitCourse(String name) {
        return new Course(name);
    }
}
