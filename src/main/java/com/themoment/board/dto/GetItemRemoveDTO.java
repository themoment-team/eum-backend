package com.themoment.board.dto;

import lombok.Data;

@Data
public class GetItemRemoveDTO {
    private String token;
    private Integer board_id;
}
