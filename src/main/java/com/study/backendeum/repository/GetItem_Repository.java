package com.study.backendeum.repository;

import com.study.backendeum.entity.GetItem_Entity;
import com.study.backendeum.entity.User_Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GetItem_Repository extends JpaRepository<GetItem_Entity, Integer> {

}