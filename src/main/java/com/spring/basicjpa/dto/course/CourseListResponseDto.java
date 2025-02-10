package com.spring.basicjpa.dto.course;

import com.spring.basicjpa.domain.Course;
import lombok.Getter;

import java.util.List;

@Getter
public class CourseListResponseDto {

    private List<CourseDto> courseList;

    private CourseListResponseDto(List<CourseDto> courseList) {
        this.courseList = courseList;
    }

    public static CourseListResponseDto createFrom(List<CourseDto> courseDtoList) {
        return new CourseListResponseDto(courseDtoList);
    }

    /**
     * CourseDto 내부 클래스
     */
    @Getter
    public static class CourseDto {
        private Long id;
        private String name;

        private CourseDto(Course course) {
            this.id = course.getId();
            this.name = course.getName();
        }

        public static CourseDto createFrom(Course course) {
            return new CourseDto(course);
        }
    }
}
