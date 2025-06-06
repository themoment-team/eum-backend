package com.themoment.board.dto;

import lombok.Data;

@Data
public class LostItemDTO {
    private String token;
    private String lostitem_name;
    private String lostitem_detail;
    private String lostitem_url_image;
}