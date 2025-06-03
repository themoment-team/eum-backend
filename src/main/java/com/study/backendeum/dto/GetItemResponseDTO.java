package com.study.backendeum.dto;

import lombok.Data;

@Data
public class GetItemResponseDTO {
    private String student_id;
    private String student_name;
    private String getitem_name;
    private String getitem_detail;
    private String getitem_url_image;
}
