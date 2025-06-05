package com.study.backendeum.dto.MainPage;

import com.study.backendeum.entity.GetItem_Entity;
import com.study.backendeum.entity.LostItem_Entity;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class CombineDTO {
    private Page<GetItem_Entity> getItems;
    private Page<LostItem_Entity> lostItems;
}
