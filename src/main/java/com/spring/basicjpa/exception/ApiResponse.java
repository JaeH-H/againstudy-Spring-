package com.spring.basicjpa.exception;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    // 속성
    private HttpStatus status; // 상태코드
    private String message; // 메시지
    private T data; // 데이터 -> DTO

    // 생성자
    private ApiResponse(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // 기능

    //성공시
    public static <T> ApiResponse<T> success(HttpStatus status, String message, T data) {
        return new ApiResponse<>(status, message, data);
    }

    //실패시
    public static <T> ApiResponse<T> error(HttpStatus status, String message) {
        return new ApiResponse<>(status, message, null);
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }
}

