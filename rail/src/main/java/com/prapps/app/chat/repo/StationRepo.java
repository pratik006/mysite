package com.prapps.app.chat.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prapps.app.rail.entity.StationEntity;

public interface StationRepo extends JpaRepository<StationEntity, Long>{
	List<StationEntity> findByType(String type);
}
