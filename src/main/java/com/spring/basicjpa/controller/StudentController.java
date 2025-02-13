package com.spring.basicjpa.controller;

import com.spring.basicjpa.dto.student.StudentCreateRequestDto;
import com.spring.basicjpa.dto.student.StudentCreateResponseDto;
import com.spring.basicjpa.dto.student.StudentCreateResponseDto;
import com.spring.basicjpa.dto.student.StudentListResponseDto;
import com.spring.basicjpa.exception.ApiResponse;
import com.spring.basicjpa.service.CourseNotFoundException;
import com.spring.basicjpa.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    /**
     * Student 생성 API
     */
    @PostMapping
    /**
     * ResponseEntity<DTO> : ResponseEntity <-> DTO
     * 로 강결합이 되어 있던 것을 아래처럼 ApiResponse를 통해서 느슨한 결합으로 바꿔준다.
     * ResponseEntity<ApiResponse<DTO>> : ResponseEntity <-> ApiResponse <-> DTO
     */
    public ResponseEntity<ApiResponse<StudentCreateResponseDto>> createCourseAPI(@RequestBody StudentCreateRequestDto studentCreateRequestDto) {

        long startTime = System.currentTimeMillis();

        try{
            //GlobalExceptionHandler를 통한 예외처리로 Controller에서는 성공만 신경쓰면 된다.
            StudentCreateResponseDto response = studentService.createStudent(studentCreateRequestDto);
            ApiResponse<StudentCreateResponseDto> apiResponse = ApiResponse.success(HttpStatus.CREATED, "created", response);
            return new ResponseEntity<ApiResponse<StudentCreateResponseDto>>(apiResponse, HttpStatus.CREATED);
        } finally {
            long endTime = System.currentTimeMillis();
            long executeTime = endTime - startTime;
            log.info("::: 실행 시간 : {}ms", executeTime);
        }

//         try {
//            StudentCreateResponseDto response = studentService.createStudent(studentCreateRequestDto);
//            return new ResponseEntity<StudentCreateResponseDto>(response, HttpStatus.CREATED);
//
//        }catch (CourseNotFoundException e) {
//            return new ResponseEntity<StudentCreateResponseDto>(HttpStatus.NOT_FOUND);
//        }
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
