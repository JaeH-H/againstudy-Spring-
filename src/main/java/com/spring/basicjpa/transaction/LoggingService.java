package com.spring.basicjpa.transaction;

import com.spring.basicjpa.domain.Course;
import com.spring.basicjpa.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoggingService {

    //속성
    private final CourseRepository courseRepository;

    //생성자

    //기능
    @Transactional
    public void processLogging() {
        Course registerCourse = Course.createInitCourse("로깅 프로세서");
        courseRepository.save(registerCourse);

        if (true) {
            throw new RuntimeException("예외발생");
        }
    }
}
