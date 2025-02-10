package com.spring.basicjpa.dto.student;

import com.spring.basicjpa.domain.Course;
import com.spring.basicjpa.domain.Student;
import lombok.Getter;

import java.util.List;

@Getter
public class StudentListResponseDto {

    private List<StudentDto> studentList;

    private StudentListResponseDto(List<StudentDto> studentDtoList) {
        this.studentList = studentDtoList;
    }

    public static StudentListResponseDto createFrom(List<StudentDto> studentDtoList) {
        return new StudentListResponseDto(studentDtoList);
    }

    @Getter
    public static class StudentDto {
        private Long id;
        private String name;
        private String courseName;

        private StudentDto(Long id, String name, String courseName) {
            this.id = id;
            this.name = name;
            this.courseName = courseName;
        }

        public static StudentDto createFrom(Student student, Course course) {
            return new StudentDto(
                    student.getId(),
                    student.getName(),
                    course.getName()
            );
        }
    }
}
