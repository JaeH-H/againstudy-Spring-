package com.spring.basic.service;

import com.spring.basic.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    //속성
    private final TodoRepository todoRepository;

    //생성자
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    //기능
    public String createTodoService(String data) {
        System.out.println(data);
        String todo = todoRepository.createTodo(data);
        return todo;
    }
}
