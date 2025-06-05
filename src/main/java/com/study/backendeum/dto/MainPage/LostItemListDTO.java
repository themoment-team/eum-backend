package com.study.backendeum.dto.MainPage;

import com.study.backendeum.entity.LostItem_Entity;
import lombok.Data;

@Data
public class LostItemListDTO {
    private String boardId;
    private String student_id;
    private String student_name;
    private String lostitem_name;
    private String lostitem_detail;
    private String lostitem_url_image;

    public LostItemListDTO(LostItem_Entity e) {
        this.boardId = String.valueOf(e.getBoardId());
        this.student_id = String.valueOf(e.getUser().getStudent_id());
        this.student_name = e.getUser().getStudent_name();
        this.lostitem_name = e.getLostitem_name();
        this.lostitem_detail = e.getLostitem_detail();
        this.lostitem_url_image = e.getLostitem_url_image();
    }
}
