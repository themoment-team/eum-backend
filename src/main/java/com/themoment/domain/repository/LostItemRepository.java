package com.themoment.domain.repository;

import com.themoment.domain.entity.LostItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostItemRepository extends JpaRepository<LostItemEntity, Long> {

}