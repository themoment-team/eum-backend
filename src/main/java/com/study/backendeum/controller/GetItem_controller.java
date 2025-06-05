package com.study.backendeum.controller;

import com.study.backendeum.dto.GetItemDTO;
import com.study.backendeum.dto.GetItemRemoveDTO;
import com.study.backendeum.dto.GetItemResponseDTO;
import com.study.backendeum.entity.GetItem_Entity;
import com.study.backendeum.entity.User_Entity;
import com.study.backendeum.repository.GetItem_Repository;
import com.study.backendeum.repository.User_Repository;
import com.study.backendeum.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
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
    public void post(@RequestBody GetItemDTO GetItemdto) {
        String token = GetItemdto.getToken();

        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }

        String student_id = jwtUtil.getStudentId(token);
        System.out.println(student_id);

        User_Entity user = user_repository.findById(Long.valueOf(student_id))
                .orElseThrow(() -> new IllegalArgumentException("없는 학번입니다."));
//        System.out.println(student_id);
//        System.out.println(user.getStudent_name());
//        System.out.println(GetItemdto.getGetitem_name());
//        System.out.println(GetItemdto.getGetitem_detail());
//        System.out.println(GetItemdto.getGetitem_url_image());
        GetItem_Entity getItem_entity = new GetItem_Entity();
        getItem_entity.setUser(user);
        getItem_entity.setStudent_name(user.getStudent_name());
        getItem_entity.setGetItem_name(GetItemdto.getGetitem_name());
        getItem_entity.setGetItem_detail(GetItemdto.getGetitem_detail());
        getItem_entity.setGetItem_url_image(GetItemdto.getGetitem_url_image());
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