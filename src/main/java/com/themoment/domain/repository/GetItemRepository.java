package com.themoment.domain.repository;

import com.themoment.domain.entity.GetItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GetItemRepository extends JpaRepository<GetItemEntity, Long> {

}