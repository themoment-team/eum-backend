package com.study.backendeum.controller;

import com.study.backendeum.dto.MainPage.*;
import com.study.backendeum.entity.GetItem_Entity;
import com.study.backendeum.entity.LostItem_Entity;
import com.study.backendeum.repository.GetItem_Repository;
import com.study.backendeum.repository.LostItem_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MainPageController {
    @Autowired
    GetItem_Repository getItem_repository;
    @Autowired
    LostItem_Repository lostItem_repository;

    @PostMapping("/list")
    public ResponseEntity<?> MainPage(@RequestBody MainPageDTO mainPageDTO) {
        int getitem_page = Integer.parseInt(mainPageDTO.getGetitem_page());
        int lostitem_page = Integer.parseInt(mainPageDTO.getLostitem_page());

        Pageable getitem_pageable = PageRequest.of(getitem_page, 5, Sort.by("boardId").descending());
        Pageable lostitem_pageable = PageRequest.of(lostitem_page, 5, Sort.by("boardId").descending());
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