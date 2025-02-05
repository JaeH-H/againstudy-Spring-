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

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    //속성
    //세션 저장소
    private final Map<String, String> sessionStore = new HashMap<>();

    //생성자

    //기능

    /**
     * 쿠키 인증 방식
     */
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

    /**
     * 세션 인증 방식
     */
    @GetMapping("/session")
    public String sessionAPI(HttpServletRequest request) {
        log.info("::: AuthController.sessionLoginAPI");

        //쿠키에서 sessionId 검색
        Cookie[] cookies = request.getCookies();
        String sessionId = findCookie("sessionId", cookies);

        // 세션 스토어에서 sessionId로 유저 데이터 조회
        if (sessionId != null) {
            String userData = sessionStore.get(sessionId);
            log.info("found user session : {}", userData);
            return "found user session" + userData;
        } else {
            return "ueser not found";
        }
    }

    @GetMapping("/session-login")
    public ResponseEntity<String> sessionLoginAPI() {
        log.info("::: AuthController.sessionLoginAPI");
        //1. 로그인 데이터 처리

        // 데이터 베이스에서 사용자 정보 조회
        String sessionId = "cat";
        String sessionData = "userId : 1";

        //2. 세션 저장소에 유저 정보 저장
        sessionStore.put(sessionId, sessionData);

        //sessionId = cat (생성 - 헤더 생성)
        String headerValue = "sessionId"+ "=" + sessionId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Set-Cookie", headerValue);

        return new ResponseEntity<>("로그인 성공", headers, HttpStatus.OK);

    }

    /**
     * 토큰 인증 방식
     */
    @GetMapping("/token")
    public String tokenAPI(HttpServletRequest request) {
        log.info("::: AuthController.tokenAPI()");
        Cookie[] cookies = request.getCookies();
        String token = this.findCookie("token", cookies);
        if (token != null) {
            // 토큰을 해독해서 유저 데이터를 활용
            String decodedToken = parseToken(token);
            log.info("found token: {}", token);
            log.info(decodedToken);

        } else {
            log.info("token not found");
        }
        return "success";
    }


    @GetMapping("/token-login")
    public ResponseEntity<String> tokenLoginAPI() {
        log.info("::: AuthController.tokenLoginAPI()");
        // 1. 로그인 로직 처리

        // 2. 데이터 베이스에서 사용자 정보 조회
        String userData = "userId: 1";

        // 3. 토큰 생성
        String encodedData = encodeToBase64(userData);

        // 4. 생성 - 헤더 생성
        String headerValue = "token" + "=" + encodedData;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Set-Cookie", headerValue);

        // 5. 응답 반환
        return new ResponseEntity<>("로그인 성공", headers, HttpStatus.OK);
    }

    private String encodeToBase64(String input) {
        byte[] encodedBytes = Base64.getEncoder().encode(input.getBytes());
        String encodedToken = new String(encodedBytes);
        return encodedToken;
    }

    private String parseToken(String token) {
        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String decodedToken = new String(decodedBytes);
        return decodedToken;
    }

    //쿠키 인증 방식에 있음
//    private String findCookie(String key, Cookie[] cookies) {
//        if (cookies != null ) {
//            for (Cookie cookie : cookies) {
//                if (key.equals(cookie.getName())) {
//                    return cookie.getValue();
//                }
//            }
//        }
//        return null;
//    }

}
