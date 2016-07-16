package com.prapps.app.trainapp.dataaccess;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prapps.app.trainapp.persistence.TrainEntity;

public interface TrainRepository extends JpaRepository<TrainEntity, Long>{
	TrainEntity findById(Long id);
	@Query("select t1 from TrainRouteEntity r1, TrainRouteEntity r2, TrainEntity t1, StationEntity s1, StationEntity s2 "
			+"where s1.id=r1.id.stationId and s2.id=r2.id.stationId and t1.id=r1.id.trainId and r1.id.trainId=r2.id.trainId and r1.haltNo < r2.haltNo "
			+ "and s1.name=:from and s2.name=:to and t1.type in (:types)")
	List<TrainEntity> search(@Param("from") String from, @Param("to") String to, @Param("types") Collection<String> types);
	
	@Query("select t1 from TrainRouteEntity r1, TrainRouteEntity r2, TrainEntity t1, StationEntity s1, StationEntity s2 "
			+"where s1.id=r1.id.stationId and s2.id=r2.id.stationId and t1.id=r1.id.trainId and r1.id.trainId=r2.id.trainId and r1.haltNo < r2.haltNo "
			+ "and s1.name=:from and s2.name=:to")
	List<TrainEntity> search(@Param("from") String from, @Param("to") String to);
}
