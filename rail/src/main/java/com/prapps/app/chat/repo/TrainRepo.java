package com.prapps.app.chat.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prapps.app.rail.dto.TrainType;
import com.prapps.app.rail.entity.TrainEntity;

public interface TrainRepo extends JpaRepository<TrainEntity, Long>{
	@Query("select r1.id.train from RouteEntity r1, RouteEntity r2"
			+ " where r1.id.train = r2.id.train and r1.halt < r2.halt and "
			+ " r1.id.station.code=:from and r2.id.station.code=:to and r1.id.train.type=:type"
			+ " and r1.departure > :departureStart and r1.departure < :departureEnd and (r1.id.train.rundays like %:rundays% or r1.id.train.rundays='Daily')")
	List<TrainEntity> findTrains(@Param("from") String from, @Param("to") String to, 
									@Param("departureStart") String departureStart, @Param("departureEnd") String departureEnd, 
									@Param("rundays") String rundays, 
									@Param("type") TrainType type, 
									Pageable pageable);
	
	@Query("select r1.id.train from RouteEntity r1, RouteEntity r2"
			+ " where r1.id.train = r2.id.train and r1.halt < r2.halt and "
			+ " r1.id.station.code=:from and r2.id.station.code=:to and r1.id.train.type=:type")
	List<TrainEntity> findTrains(@Param("from") String from, @Param("to") String to, 
									@Param("type") TrainType type, 
									Pageable pageable);
}
