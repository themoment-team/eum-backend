package com.themoment.board.dto;

import lombok.Data;

@Data
public class LostItemResponseDTO {
    private String student_id;
    private String student_name;
    private String lostitem_name;
    private String lostitem_detail;
    private String lostitem_url_image;
}
