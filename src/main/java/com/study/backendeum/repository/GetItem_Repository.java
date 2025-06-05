package com.study.backendeum.repository;

import com.study.backendeum.entity.GetItem_Entity;
import com.study.backendeum.entity.User_Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GetItem_Repository extends JpaRepository<GetItem_Entity, Long> {

}