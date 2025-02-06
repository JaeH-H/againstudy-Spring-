package com.spring.basic.dto;

import com.spring.basic.domain.Student;
import lombok.Getter;

@Getter
public class StudentCreateResponseDto {

    private String mesage;
    private Long studentId;

    private StudentCreateResponseDto(String mesage, Long studentId) {
        this.mesage = mesage;
        this.studentId = studentId;
    }

    //팩토리 패턴
    public static StudentCreateResponseDto of (Student student) {
        return new StudentCreateResponseDto("create", student.getId());
    }
}
