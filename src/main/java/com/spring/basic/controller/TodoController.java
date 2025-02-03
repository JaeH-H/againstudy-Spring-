package com.spring.basic.controller;

import com.spring.basic.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Provider;

@RestController
@RequestMapping("/todo")
public class TodoController {
    //속성
    private final TodoService todoService;

    //생성자
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }



    //기능
    // ::: 일정 생성 API
    @PostMapping
    public String createTodoAPI() {
        String data = "데이터";
        String todo = todoService.createTodoService(data);
        return todo;
    }

    // ::: 일정 조회 API
    @GetMapping
    public void getTodoAPI() {

    }
}
