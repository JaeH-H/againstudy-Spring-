package com.spring.basicjpa.jwt;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtils jwtUtils;

    /**
     * 로그인 API
     * 학생의 email, password 를 받아 로그인 시도를합니다.
     * 로그인 성공시 JWT 토큰을 생성해 클라이언트로 반환합니다.
     * @return token
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/login")
    public String loginAPI() throws UnsupportedEncodingException {
        // 1. 로그인을 성공했다고 가정합니다.

        // 2. 토큰에 담을 정보 준비
        Long studentId = 1L;     // 학생 식별자
        String role = "student"; //
        String dataThatYouWannaPut = "내 마음대로 데이터";

        // 3. 토큰 발급
        String token = jwtUtils.createToken(studentId, role, dataThatYouWannaPut);
        return token;
    }

    /**
     * 인증이 필요한 API
     * 이 API는 로그인한 사용자만 접근할 수 있는  예제입니다.
     * Authorization 헤더에서 JWT를 추출하여 사용자를 인증합니다.
     * 주석을 활용해서 일반 토큰 Bearer 토큰을 Authorization 헤더에서 추출해서 활용합니다.
     *
     * @param request Authorization 헤더를 포함한 요청 정보를 전달 받습니다.
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/api")
    public void authRequiredAPI(HttpServletRequest request) throws UnsupportedEncodingException {

        // 1. requestHeader 에서 토큰 추출
        String token = request.getHeader("Authorization");

        // 2. 토큰에서 정보 추출
        // 2-1. 일반 토큰 사용할 경우
        Long studentId = jwtUtils.extractStudentIdFromToken(token);

        // 2-2.베어러 토큰 사용할 경우
        // Long studentId = jwtService.decodeBearerToken(token);

        // 3. 학생 아이디 활용
        log.info("학생 아이디는: {}", studentId);
    }
}
