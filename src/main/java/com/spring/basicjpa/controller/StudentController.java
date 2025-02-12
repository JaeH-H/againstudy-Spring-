package com.spring.basicjpa.controller;

import com.spring.basicjpa.dto.student.StudentCreateRequestDto;
import com.spring.basicjpa.dto.student.StudentCreateResponseDto;
import com.spring.basicjpa.dto.student.StudentCreateResponseDto;
import com.spring.basicjpa.dto.student.StudentListResponseDto;
import com.spring.basicjpa.service.CourseNotFoundException;
import com.spring.basicjpa.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    /**
     * Student 생성 API
     */
    @PostMapping
    public ResponseEntity<StudentCreateResponseDto> createCourseAPI(@RequestBody StudentCreateRequestDto studentCreateRequestDto) {
//        try {
//            StudentCreateResponseDto response = studentService.createStudent(studentCreateRequestDto);
//            return new ResponseEntity<StudentCreateResponseDto>(response, HttpStatus.CREATED);
//
//        }catch (CourseNotFoundException e) {
//            return new ResponseEntity<StudentCreateResponseDto>(HttpStatus.NOT_FOUND);
//        }

        //GlobalExceptionHandler를 통한 예외처리로 Controller에서는 성공만 신경쓰면 된다.
        StudentCreateResponseDto response = studentService.createStudent(studentCreateRequestDto);
        return new ResponseEntity<StudentCreateResponseDto>(response, HttpStatus.CREATED);
    }

    /**
     * Student 조회 API
     */
    @GetMapping
    public ResponseEntity<StudentListResponseDto> getCourseListAPI() {
        StudentListResponseDto response = studentService.getStudentList();
        return new ResponseEntity<StudentListResponseDto>(response, HttpStatus.OK);
    }
}
