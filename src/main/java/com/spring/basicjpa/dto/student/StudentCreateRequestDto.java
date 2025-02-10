package com.spring.basicjpa.dto.student;

import lombok.Getter;

@Getter
public class StudentCreateRequestDto {
    private String studentName;
    private Long courseId;
}
