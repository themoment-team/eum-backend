package com.themoment.login.member.controller;

import com.themoment.exception.ExceptionDTO;
import com.themoment.login.member.DTO.LoginResponseDTO;
import com.themoment.login.member.DTO.UserLoginDTO;
import com.themoment.login.member.DTO.UserSignupDTO;
import com.themoment.login.member.JWT.JWTUtil;
import com.themoment.domain.entity.UserEntity;
import com.themoment.domain.repository.UserRepository;
import com.themoment.login.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final UserRepository userRepository;
    private final MemberService memberService;
    private final JWTUtil jwtUtil;


    // API 회원가입 (JSON 요청)
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserSignupDTO dto) {
        try {
            dto.setEmail(dto.getEmail().toLowerCase());
            memberService.signup(dto);
            return ResponseEntity.status(201).body(new ExceptionDTO("회원가입 성공!"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(402).body(new ExceptionDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ExceptionDTO(e.getMessage()));
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody UserLoginDTO dto) {
        String email = dto.getEmail().toLowerCase();
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            if (user.getPassword().equals(dto.getPassword())) {
                String token = jwtUtil.generateToken(user); // JWT 발급
                LoginResponseDTO responseDTO = new LoginResponseDTO("로그인 성공!",token, user.getStudent_name());
                return ResponseEntity.status(200).body(responseDTO);
            } else {
                return ResponseEntity.status(403).body(new ExceptionDTO("비밀번호가 올바르지 않습니다."));
            }
        } else {
            return ResponseEntity.status(404).body(new ExceptionDTO("존재하지 않는 이메일입니다."));
        }
    }
}



