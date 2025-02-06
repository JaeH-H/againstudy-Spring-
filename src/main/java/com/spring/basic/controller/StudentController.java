package com.spring.basic.controller;


import com.spring.basic.dto.StudentCreateRequestDto;
import com.spring.basic.dto.StudentCreateResponseDto;
import com.spring.basic.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@Slf4j
@RequiredArgsConstructor
public class StudentController {

    //속성
    private final StudentService studentService;

    //생성자

    //기능

    /**
     * json
     * 요청 ------
     * {
     *     "name" : "Name"
     * }
     *
     * 응답 ------
     * {
     *     "message" : "create",
     *     "studentId" : "id"
     * }
     */
    @PostMapping
    public ResponseEntity<StudentCreateResponseDto> createStudentAPI(@RequestBody StudentCreateRequestDto studentCreateRequestDto) {

        log.info("studentName : {}", studentCreateRequestDto.getName());
        StudentCreateResponseDto studentCreateResponseDto = studentService.createStudentService(studentCreateRequestDto);

        //응답 임시
//        StudentCreateResponseDto studentCreateResponseDto = new StudentCreateResponseDto();

        return new ResponseEntity<StudentCreateResponseDto>(studentCreateResponseDto, HttpStatus.CREATED);

    }
}
