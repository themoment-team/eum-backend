package com.themoment.board.repository;

import com.themoment.board.entity.User_Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User_Repository extends JpaRepository<User_Entity, Long> {

}