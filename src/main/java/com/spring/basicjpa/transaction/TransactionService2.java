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
}
