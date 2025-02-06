package com.spring.basic.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "students")
@Getter
public class Student {
    //속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //생성자
    //JPA는 기본적으로 기본생성자를 활용한다.
    //그래서 명시해주는 게 좋다.
    protected Student() {}

    private Student(String name) {
        this.name = name;
    }

    //dto 변환 로직 담당 기능(팩토리 패턴) -> 코드가 커질수록 생성자가 많아지기 때문에 사용
    public static Student createFromDto(String name) {
        return new Student(name);
    }


}
