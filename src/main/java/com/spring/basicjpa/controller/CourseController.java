package com.spring.basicjpa.controller;

import com.spring.basicjpa.dto.course.CourseCreateRequestDto;
import com.spring.basicjpa.dto.course.CourseCreateResponseDto;
import com.spring.basicjpa.dto.course.CourseListResponseDto;
import com.spring.basicjpa.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    /**
     * Course 생성 API
     */
    @PostMapping
    public ResponseEntity<CourseCreateResponseDto> createCourseAPI(@RequestBody CourseCreateRequestDto courseCreateRequestDto) {
        CourseCreateResponseDto response = courseService.createCourse(courseCreateRequestDto);
        return new ResponseEntity<CourseCreateResponseDto>(response, HttpStatus.CREATED);
    }

    /**
     * Course 조회 API
     */
    @GetMapping
    public ResponseEntity<CourseListResponseDto> getCourseListAPI() {
        CourseListResponseDto response = courseService.getCourseList();
        return new ResponseEntity<CourseListResponseDto>(response, HttpStatus.CREATED);
    }

    @GetMapping("/playground")
    public String testAPI() {
        courseService.testService();
        return "success";
    }
}
