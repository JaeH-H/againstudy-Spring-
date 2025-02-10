package com.spring.basicjpa.dto.course;

import com.spring.basicjpa.domain.Course;
import lombok.Getter;

@Getter
public class CourseCreateResponseDto {
    private String message;
    private Long courseId;

    private CourseCreateResponseDto(String message, Long courseId) {
        this.message = message;
        this.courseId = courseId;
    }

    public static CourseCreateResponseDto createFrom(Course course) {
        Long courseId = course.getId();
        return new CourseCreateResponseDto("created", courseId);
    }
}
