package com.themoment.board.service;

import com.themoment.board.dto.LostItemDTO;
import com.themoment.board.dto.LostItemResponseDTO;
import com.themoment.domain.entity.LostItemEntity;
import com.themoment.domain.entity.UserEntity;
import com.themoment.domain.repository.LostItemRepository;
import com.themoment.domain.repository.UserRepository;
import com.themoment.board.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LostItemService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private LostItemRepository lostItemRepository;

    public void post(LostItemDTO lostItemDTO) {
        String token = lostItemDTO.getToken();

        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
        Long student_id = Long.valueOf(jwtUtil.getStudentId(token));
        UserEntity user = UserRepository.findById(student_id)
            .orElseThrow(() -> new IllegalArgumentException("없는 학번입니다."));

        LostItemEntity lostItemEntity = new LostItemEntity();
        lostItemEntity.setUser(user);
        lostItemEntity.setStudent_name(user.getStudent_name());
        lostItemEntity.setLostitem_name(lostItemDTO.getLostitem_name());
        lostItemEntity.setLostitem_detail(lostItemDTO.getLostitem_detail());
        lostItemEntity.setLostitem_url_image(lostItemDTO.getLostitem_url_image());
        lostItemRepository.save(lostItemEntity);
    }

    public LostItemResponseDTO view(Long boardId) {
        LostItemResponseDTO lostItemResponseDTO = new LostItemResponseDTO();
        LostItemEntity board = lostItemRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        lostItemResponseDTO.setStudent_id(String.valueOf(board.getUser().getStudent_id()));
        lostItemResponseDTO.setStudent_name(board.getStudent_name());
        lostItemResponseDTO.setLostitem_name(board.getLostitem_name());
        lostItemResponseDTO.setLostitem_detail(board.getLostitem_detail());
        lostItemResponseDTO.setLostitem_url_image(board.getLostitem_url_image());
        return lostItemResponseDTO;
    }
}
