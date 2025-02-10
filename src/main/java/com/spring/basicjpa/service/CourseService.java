package com.spring.basicjpa.service;

import com.spring.basicjpa.domain.Student;
import com.spring.basicjpa.dto.course.CourseCreateRequestDto;
import com.spring.basicjpa.dto.course.CourseCreateResponseDto;
import com.spring.basicjpa.domain.Course;
import com.spring.basicjpa.dto.course.CourseListResponseDto;
import com.spring.basicjpa.repository.CourseRepository;
import com.spring.basicjpa.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    /**
     * Course 생성 서비스
     * @param courseCreateRequestDto
     * @return CourseCreateResponseDto
     */
    @Transactional
    public CourseCreateResponseDto createCourse(CourseCreateRequestDto courseCreateRequestDto) {
        Course newCourse = Course.createFrom(courseCreateRequestDto);
        Course savedCourse = courseRepository.save(newCourse);
        return CourseCreateResponseDto.createFrom(savedCourse);
    }

    /**
     * Course 조회 서비스
     */
    public CourseListResponseDto getCourseList() {
        List<Course> foundCourseList = courseRepository.findAll();
        List<CourseListResponseDto.CourseDto> courseDtoList = foundCourseList.stream()
                .map(CourseListResponseDto.CourseDto::createFrom)
                .toList();
        return CourseListResponseDto.createFrom(courseDtoList);
    }

    @Transactional
    public void testService() {
        // 양방향 관계를 설정(주석해제)하고 체험해보세요.
//        Course foundCourse = courseRepository.findById(1L).get();
//        List<Student> studentList = foundCourse.getStudents();
//        studentList.remove(0);
    }
}
