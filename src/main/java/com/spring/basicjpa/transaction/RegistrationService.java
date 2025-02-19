package com.spring.basicjpa.transaction;

import com.spring.basicjpa.domain.Course;
import com.spring.basicjpa.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    //속성
    private final CourseRepository courseRepository;

    //생성자

    //기능
    @Transactional
    public void processRegistration() {
        Course registerCourse = Course.createInitCourse("등록 프로세서");
        courseRepository.save(registerCourse);
    }
}
