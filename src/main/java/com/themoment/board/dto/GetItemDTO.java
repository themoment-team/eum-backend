package com.themoment.board.dto;

import lombok.Data;

@Data
public class GetItemDTO {
    private String token;
    private String getitem_name;
    private String getitem_detail;
    private String getitem_url_image;
}