// 테스트를 위한 토큰 생성용

package com.study.backendeum.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.study.backendeum.entity.User_Entity;
import com.study.backendeum.repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class JwtTokenGenerator {
    @Autowired
    private User_Repository user_repository;

    @Value("${jwt.secret}")
    String SECRET_KEY;

    @PostMapping("/login")
    public String login(@RequestBody User_Entity user) {

        Date now = new Date();
        user_repository.save(user);


        return JWT.create()
                .withIssuer("myServer")           // 발급자
                .withSubject(String.valueOf(user.getStudent_id()))           // 사용자 식별자 (예: 사용자명)
                .withClaim("name", user.getStudent_name())       // 추가 정보 (예: 사용자 역할)
                .withIssuedAt(now)           // 발급 시간
                .withExpiresAt(new Date(now.getTime() + 3600_000))           // 만료 시간
                .sign(Algorithm.HMAC256(SECRET_KEY)); // HMAC 서명

    }
}