package com.spring.basicjpa.dto.student;

import com.spring.basicjpa.domain.Student;
import lombok.Getter;

@Getter
public class StudentCreateResponseDto {
    private String message;
    private Long studentId;

    private StudentCreateResponseDto(String message, Long studentId) {
        this.message = message;
        this.studentId = studentId;
    }

    public static StudentCreateResponseDto createFrom(Student student) {
        return new StudentCreateResponseDto(
                "created",
                student.getId()
        );
    }
}
