package com.themoment.board.dto.MainPage;

import com.themoment.board.entity.GetItem_Entity;
import com.themoment.board.entity.LostItem_Entity;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class CombineDTO {
    private Page<GetItem_Entity> getItems;
    private Page<LostItem_Entity> lostItems;
}
