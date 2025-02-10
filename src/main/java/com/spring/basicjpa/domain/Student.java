package com.spring.basicjpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity
@Table(name = "student")
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    protected Student() {}

    private Student(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static Student createFrom(String studentName, Course course) {
        return new Student(studentName, course);
    }

    public static Student createInitStudent(String name, Course course) {
        return new Student(name, course);
    }
}
