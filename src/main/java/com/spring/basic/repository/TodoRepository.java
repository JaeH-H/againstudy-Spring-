package com.spring.basic.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TodoRepository {

    public String createTodo(String data) {
        return "success data";
    }
}
