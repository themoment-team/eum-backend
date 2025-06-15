package com.themoment.board.controller;

import com.themoment.board.dto.GetItemDTO;
import com.themoment.board.dto.GetItemResponseDTO;
import com.themoment.board.entity.GetItem_Entity;
import com.themoment.board.entity.User_Entity;
import com.themoment.board.repository.GetItem_Repository;
import com.themoment.board.repository.User_Repository;
import com.themoment.board.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/getitem")
public class GetItem_controller {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private User_Repository user_repository;
    @Autowired
    private GetItem_Repository getItem_repository;

    @PostMapping("/post")
    public void post(@RequestBody(required = false) GetItemDTO GetItemdto) {
        String token = GetItemdto.getToken();

        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }

        String student_id = jwtUtil.getStudentId(token);

        User_Entity user = user_repository.findById(Long.valueOf(student_id))
                .orElseThrow(() -> new IllegalArgumentException("없는 학번입니다."));

        GetItem_Entity getItem_entity = new GetItem_Entity();
        getItem_entity.setUser(user);
        getItem_entity.setStudent_name(user.getStudent_name());
        getItem_entity.setGetItem_name(GetItemdto.getGetitem_name());
        getItem_entity.setGetItem_detail(GetItemdto.getGetitem_detail());
        Optional.ofNullable(GetItemdto.getGetitem_url_image())
                .orElseThrow(() -> new IllegalArgumentException("이미지 URL은 필수입니다."));
        getItem_repository.save(getItem_entity);
    }

    @GetMapping("/view/{boardid}")
    public ResponseEntity<?> View(@PathVariable Integer boardid) {
        GetItem_Entity board = getItem_repository.findById(Long.valueOf(boardid))
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        GetItemResponseDTO response = new GetItemResponseDTO();
        response.setStudent_id(String.valueOf(board.getUser().getStudent_id()));
        response.setStudent_name(board.getStudent_name());
        response.setGetitem_name(board.getGetItem_name());
        response.setGetitem_detail(board.getGetItem_detail());
        response.setGetitem_url_image(board.getGetItem_url_image());
        return ResponseEntity.ok(response);
    }
}