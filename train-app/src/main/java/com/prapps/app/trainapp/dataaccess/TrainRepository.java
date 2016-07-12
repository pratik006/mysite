package com.prapps.app.trainapp.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prapps.app.trainapp.persistence.TrainEntity;

public interface TrainRepository extends JpaRepository<TrainEntity, Long>{
	TrainEntity findById(Long id);
}
