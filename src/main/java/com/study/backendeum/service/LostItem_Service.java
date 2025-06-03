package com.study.backendeum.service;

import com.study.backendeum.repository.LostItem_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LostItem_Service {
    @Autowired
    private LostItem_Repository lostitem_repository;

}
