package com.spring.basicjpa.service;

public class CourseNotFoundException extends RuntimeException{
    //속성
    private String errorMessage;

    //생성자
    public CourseNotFoundException(String message) {
        this.errorMessage = message;
//        super(message);
    }

    //기능
}
