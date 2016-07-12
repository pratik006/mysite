package com.prapps.app.trainapp.dataaccess;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prapps.app.trainapp.persistence.StationEntity;

public interface StationRepository extends JpaRepository<StationEntity, Long> {
	List<StationEntity> findByNameContaining(String match);
	StationEntity findByCode(String code);
	List<StationEntity> findByNameIn(Collection<String> names);
}
