package com.themoment.board.dto.MainPage;

import com.themoment.board.entity.GetItem_Entity;
import lombok.Data;

@Data
public class GetItemListDTO {
    private String boardId;
    private String student_id;
    private String student_name;
    private String getitem_name;
    private String getitem_detail;
    private String getitem_url_image;

    public GetItemListDTO(GetItem_Entity e) {
        this.boardId = String.valueOf(e.getBoardId());
        this.student_id = String.valueOf(e.getUser().getStudent_id());
        this.student_name = e.getUser().getStudent_name();
        this.getitem_name = e.getGetItem_name();
        this.getitem_detail = e.getGetItem_detail();
        this.getitem_url_image = e.getGetItem_url_image();
    }
}