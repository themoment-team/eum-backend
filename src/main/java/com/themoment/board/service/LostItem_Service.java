package com.themoment.board.service;

import com.themoment.board.repository.LostItem_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LostItem_Service {
    @Autowired
    private LostItem_Repository lostitem_repository;

}
