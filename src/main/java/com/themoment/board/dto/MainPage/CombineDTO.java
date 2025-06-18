package com.themoment.board.dto.MainPage;

import com.themoment.domain.entity.GetItemEntity;
import com.themoment.domain.entity.LostItemEntity;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class CombineDTO {
    private Page<GetItemEntity> getItems;
    private Page<LostItemEntity> lostItems;
}
