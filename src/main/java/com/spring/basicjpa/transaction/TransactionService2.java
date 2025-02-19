package com.spring.basicjpa.transaction;

import com.spring.basicjpa.domain.Course;
import com.spring.basicjpa.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService2 {
    //속성
    private final CourseRepository courseRepository;

    //생성자

    //기능

    /**
     * 기본 롤백 정책
     * 언체크예외만 롤백 시킨다.
     */
    @Transactional(noRollbackFor = RuntimeException.class)
    public void defaultRollbackService() throws Exception {

        // 1. 작업단위 : 수업A 생성
        Course courseA = Course.createInitCourse("수업A");
        courseRepository.save(courseA);

        // 2. 작업단위 : 수업B 생성
        Course courseB = Course.createInitCourse("수업B");
        courseRepository.save(courseB);

        if(true) {
//            throw new RuntimeException("예외발생");
            throw new Exception("예외발생");
        }
    }

    @Transactional
    public void processTransaction() {

        //작업단위1 : 등록 프로세서
        processRegistration();

        //작업단위2 : 결제 프로세서
        processPayment();

        //작업단위3 : 로깅 프로세서
        processLogging();

    }

    public void processRegistration() {
        Course registerCourse = Course.createInitCourse("등록 프로세서");
        courseRepository.save(registerCourse);
    }

    public void processPayment() {
        Course registerCourse = Course.createInitCourse("결제 프로세스");
        courseRepository.save(registerCourse);
    }

    public void processLogging() {
        Course registerCourse = Course.createInitCourse("로깅 프로세서");
        courseRepository.save(registerCourse);

        if(true) {
            throw new RuntimeException("예외발생");
        }
    }
}
