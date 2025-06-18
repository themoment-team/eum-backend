package com.themoment.board.service;

import com.themoment.board.dto.GetItemDTO;
import com.themoment.board.dto.GetItemResponseDTO;
import com.themoment.board.security.JwtUtil;
import com.themoment.domain.entity.GetItemEntity;
import com.themoment.domain.entity.UserEntity;
import com.themoment.domain.repository.GetItemRepository;
import com.themoment.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetItemService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private GetItemRepository getItemRepository;

    public void post(GetItemDTO getItemDTO) {
        String token = getItemDTO.getToken();

        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }

        Long student_id = Long.valueOf(jwtUtil.getStudentId(token));
        UserEntity user = UserRepository.findById(student_id)
            .orElseThrow(() -> new IllegalArgumentException("없는 학번입니다."));

        GetItemEntity getItemEntity = new GetItemEntity();
        getItemEntity.setUser(user);
        getItemEntity.setStudent_name(user.getStudent_name());
        getItemEntity.setGetItem_name(getItemDTO.getGetitem_name());
        getItemEntity.setGetItem_detail(getItemDTO.getGetitem_detail());
        getItemEntity.setGetItem_url_image(getItemDTO.getGetitem_url_image());
        getItemRepository.save(getItemEntity);
    }

    public GetItemResponseDTO view(Long boardId) {
        GetItemResponseDTO getItemResponseDTO = new GetItemResponseDTO();
        GetItemEntity board = getItemRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        getItemResponseDTO.setStudent_id(String.valueOf(board.getUser().getStudent_id()));
        getItemResponseDTO.setStudent_name(board.getStudent_name());
        getItemResponseDTO.setGetitem_name(board.getGetItem_name());
        getItemResponseDTO.setGetitem_detail(board.getGetItem_detail());
        getItemResponseDTO.setGetitem_url_image(board.getGetItem_url_image());
        return getItemResponseDTO;
    }
}
