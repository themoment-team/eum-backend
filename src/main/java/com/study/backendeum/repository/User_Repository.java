package com.study.backendeum.repository;

import com.study.backendeum.entity.User_Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User_Repository extends JpaRepository<User_Entity, Integer> {

}