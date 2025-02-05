package com.spring.basic.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    //속성

    //생성자

    //기능
    @GetMapping("/cookie")
    public String cookieAPI(HttpServletRequest request) {
        log.info("::: AuthController.cookieAPI");
        // 서비스 로직1 / 오류가 발생했을 때 로그를 서비스에 찍어 보면서 범위를 좁힐 수 있다.
        log.info("debug1");

        //쿠키를 받아 오는 것
        //1. Request 객체에서 cookie 목록 가져오기
        Cookie[] cookies = request.getCookies();

        //2. 쿠키 (userId) 키값으로 value 찾는 함수 호출
        String foundUserId = findCookie("userId", cookies);

        //3. 찾아온 쿠키의 값 출력
        if (foundUserId != null) {
            log.info("found userId : {}", foundUserId);
        } else {
            log.info("userId not found");
        }
        return "success";
    }

    private String findCookie(String key, Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @GetMapping("/cookie-login")
    public ResponseEntity<String> cookieLoginAPI() {
        log.info("::: AuthController.cookieLoginAPI()");
        //1. 로그인 로직
        String cookieKey = "userId";
        String userId = "1";

        //2. 응답 생성
        String headerValue = cookieKey + "=" + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Set-Cookie", headerValue);
        return new ResponseEntity<>("login success", headers, HttpStatus.OK);
    }
}
