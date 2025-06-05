package com.study.backendeum.dto.MainPage;

import lombok.Data;

import java.util.List;

@Data
public class ItemListDTO<T> {
    private List<T> content;
    private int page;

    public ItemListDTO(List<T> content, int page) {
        this.content = content;
        this.page = page;
    }
}
