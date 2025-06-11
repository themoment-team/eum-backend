package com.themoment.board.controller;

import com.themoment.board.dto.MainPage.CombineItemListDTO;
import com.themoment.board.dto.MainPage.GetItemListDTO;
import com.themoment.board.dto.MainPage.ItemListDTO;
import com.themoment.board.dto.MainPage.LostItemListDTO;
import com.themoment.board.entity.GetItem_Entity;
import com.themoment.board.entity.LostItem_Entity;
import com.themoment.board.repository.GetItem_Repository;
import com.themoment.board.repository.LostItem_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainPageController {
    @Autowired
    GetItem_Repository getItem_repository;
    @Autowired
    LostItem_Repository lostItem_repository;

    @GetMapping("/main")
    public ResponseEntity<?> MainPage() {
//        int getitem_page = Integer.parseInt(mainPageDTO.getGetitem_page());
//        int lostitem_page = Integer.parseInt(mainPageDTO.getLostitem_page());

        Pageable getitem_pageable = PageRequest.of(0, 50, Sort.by("boardId").descending());
        Pageable lostitem_pageable = PageRequest.of(0, 50, Sort.by("boardId").descending());
        Page<GetItem_Entity> getItems = getItem_repository.findAll(getitem_pageable);
        Page<LostItem_Entity> lostItems = lostItem_repository.findAll(lostitem_pageable);

        List<GetItemListDTO> getItemDTOs = getItems.getContent().stream()
                .map(GetItemListDTO::new)
                .collect(Collectors.toList());

        List<LostItemListDTO> lostItemDTOs = lostItems.getContent().stream()
                .map(LostItemListDTO::new)
                .collect(Collectors.toList());

        ItemListDTO<GetItemListDTO> getitem = new ItemListDTO<>(getItemDTOs,getItems.getNumber());
        ItemListDTO<LostItemListDTO> lostitem = new ItemListDTO<>(lostItemDTOs,lostItems.getNumber());

        return ResponseEntity.ok(new CombineItemListDTO(getitem,lostitem));
    }
}