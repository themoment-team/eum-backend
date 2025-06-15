package com.themoment.board.controller;

import com.themoment.board.dto.GetItemResponseDTO;
import com.themoment.board.dto.LostItemDTO;
import com.themoment.board.entity.LostItem_Entity;
import com.themoment.board.entity.User_Entity;
import com.themoment.board.repository.LostItem_Repository;
import com.themoment.board.repository.User_Repository;
import com.themoment.board.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lostitem")
public class LostItem_controller {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private User_Repository user_repository;
    @Autowired
    private LostItem_Repository lostItem_repository;

    @PostMapping("/post")
    public void post(@RequestBody(required = false) LostItemDTO LostItemdto) {
        String token = LostItemdto.getToken();

        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }

        String student_id = jwtUtil.getStudentId(token);

        User_Entity user = user_repository.findById(Long.valueOf(student_id))
                .orElseThrow(() -> new IllegalArgumentException("없는 학번입니다."));
//        System.out.println(student_id);
//        System.out.println(user.getStudent_name());
//        System.out.println(GetItemdto.getGetitem_name());
//        System.out.println(GetItemdto.getGetitem_detail());
//        System.out.println(GetItemdto.getGetitem_url_image());
        LostItem_Entity lostItem_entity = new LostItem_Entity();
        lostItem_entity.setUser(user);
        lostItem_entity.setStudent_name(user.getStudent_name());
        lostItem_entity.setLostitem_name(LostItemdto.getLostitem_name());
        lostItem_entity.setLostitem_detail(LostItemdto.getLostitem_detail());
        lostItem_entity.setLostitem_url_image(LostItemdto.getLostitem_url_image());
        lostItem_repository.save(lostItem_entity);
    }

    @GetMapping("/view/{boardid}")
    public ResponseEntity<?> View(@PathVariable Integer boardid) {
        LostItem_Entity board = lostItem_repository.findById(Long.valueOf(boardid))
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        GetItemResponseDTO response = new GetItemResponseDTO();
        response.setStudent_id(String.valueOf(board.getUser().getStudent_id()));
        response.setStudent_name(board.getStudent_name());
        response.setGetitem_name(board.getLostitem_name());
        response.setGetitem_detail(board.getLostitem_detail());
        response.setGetitem_url_image(board.getLostitem_url_image());
        return ResponseEntity.ok(response);
    }
}