package com.prapps.app.trainapp.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prapps.app.trainapp.persistence.TrainRouteEntity;
import com.prapps.app.trainapp.persistence.TrainRoutePk;

public interface TrainRouteRepository extends JpaRepository<TrainRouteEntity, TrainRoutePk> {
	
}
