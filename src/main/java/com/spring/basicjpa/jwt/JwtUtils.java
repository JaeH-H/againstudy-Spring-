package com.spring.basicjpa.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JwtUtils {

    // SECRET 의 경우 스프링설정파일(application.properties) 에서 주입받아 활용가능합니다.
    //VERIFY SIGNATURE에 들어가는 암호
    private static final String SECRET = "my-cat-is-flying";
    private static final String BEARER_PREFIX = "Bearer";

    /**
     * jwt 토큰 발급
     *
     * 토큰 생성에 필요한 정보를 매개변수로 받아 JWT 토큰을 생성하고 반환합니다.
     * @param studentId 학생 식별자
     * @param role 학생 권한
     * @param dataYouWannaPut 토큰에 넣고 싶은 데이터
     * @return token
     * @throws UnsupportedEncodingException
     */
    public String createToken(Long studentId, String role, String dataYouWannaPut) throws UnsupportedEncodingException {
        // 1. 토큰 서명에 활용될 알고리즘 설정
        //VERIFY SIGNATURE에 들어가는 암호
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        // 2. 토큰 생성
        String token = JWT.create()
                .withIssuer("sparta.basic.com")
                .withSubject(studentId.toString())
                .withClaim("role", role)
                .withClaim("customField1", dataYouWannaPut)
                .withClaim("customField2", "customFieldValue2")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
                .sign(algorithm); // 서명
        return token;
    }

    /**
     * 토큰에서 학생 식별자를 추출 합니다.
     * @param token 일반 토큰
     * @return studentId(Long) 학생 식별자
     * @throws UnsupportedEncodingException
     */
    public Long extractStudentIdFromToken(String token) throws UnsupportedEncodingException {
        // 1. 토큰에 활용할 알고리즘 설정
        //VERIFY SIGNATURE에 들어가는 암호
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        // 2. 토큰 검증
        DecodedJWT decodedToken = JWT.require(algorithm)
                .withIssuer("sparta.basic.com")
                .build()
                .verify(token);

        // 3. 토큰에서 원하는 정보 추출
        System.out.println("subject: " + decodedToken.getSubject());
        System.out.println("role: " + decodedToken.getClaim("role").asString());
        System.out.println("customField1: " + decodedToken.getClaim("customField1").asString());
        System.out.println("customField2: " + decodedToken.getClaim("customField2").asString());
        System.out.println("issued At: " + decodedToken.getIssuedAt());
        System.out.println("expires At: " + decodedToken.getExpiresAt());

        // 4. 학생 식별자 반환
        String studentId = decodedToken.getSubject();
        return Long.parseLong(studentId);
    }

    /**
     * 베어러 토큰에서 학생 식별자를 추출 합니다.
     * @param bearerToken
     * @return studentId(Long) 학생 식별자
     * @throws UnsupportedEncodingException
     */
    public Long extractStudentIdFromBearerToken(String bearerToken) throws UnsupportedEncodingException {
        // 1. 토큰에 활용할 알고리즘
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        // 2. Bearer 토큰 추출
        String token = bearerToken.substring(BEARER_PREFIX.length()).trim();

        // 3. 토큰 검증
        DecodedJWT decodedToken = JWT.require(algorithm)
                .withIssuer("sparta.basic.com")
                .build()
                .verify(token);

        // 4. 토큰에서 원하는 정보 추출
        System.out.println("subject: " + decodedToken.getSubject());
        System.out.println("role: " + decodedToken.getClaim("role").asString());
        System.out.println("customField1: " + decodedToken.getClaim("customField1").asString());
        System.out.println("customField2: " + decodedToken.getClaim("customField2").asString());
        System.out.println("issued At: " + decodedToken.getIssuedAt());
        System.out.println("expires At: " + decodedToken.getExpiresAt());

        // 5. 학생 식별자 반환
        String studentId = decodedToken.getSubject();
        return Long.parseLong(studentId);
    }
}
