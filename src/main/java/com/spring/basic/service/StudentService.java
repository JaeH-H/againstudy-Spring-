package com.spring.basic.service;

import com.spring.basic.domain.Student;
import com.spring.basic.dto.StudentCreateRequestDto;
import com.spring.basic.dto.StudentCreateResponseDto;
import com.spring.basic.repository.JPAStudentRepository;
import com.spring.basic.repository.SpringDataStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    //속성
//    //순수 JPA 활용 Repository
//    private final JPAStudentRepository repository;

    //JPA 인터페이스 활용
    private final SpringDataStudentRepository repository;

    //생성자

    //기능
    public StudentCreateResponseDto createStudentService(StudentCreateRequestDto studentCreateRequestDto) {
        String studentName = studentCreateRequestDto.getName();

        //팩토리 패턴을 사용하면 어떤 의미로 생성자를 사용하는지 알 수 있다.
        Student newStudent = Student.createFromDto(studentName);

//        //생성자를 어떤 의미로 사용하는지 많아질수록 헷갈리게 된다.
//        new Student(x);
//        new Student(x, xx);
//        new Student(x, xx, xxx);

        Student savedStudent = repository.save(newStudent);

        return StudentCreateResponseDto.of(savedStudent);

    }

}
