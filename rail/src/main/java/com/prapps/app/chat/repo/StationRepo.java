package com.prapps.app.chat.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prapps.app.rail.entity.StationEntity;

public interface StationRepo extends JpaRepository<StationEntity, Long>{
	List<StationEntity> findByType(String type);
	@Query("SELECT s"
			+ " FROM StationEntity s "
			+ " where SQRT(POW(69.1 * (xcood - :lat), 2) +POW(69.1 * (:lon- ycood) * COS(xcood / 57.3), 2)) < 25 "
			+ " order by SQRT(POW(69.1 * (xcood - :lat), 2) +POW(69.1 * (:lon- ycood) * COS(xcood / 57.3), 2)) asc")
	List<StationEntity> getNearestStations(@Param("lat") double lat, @Param("lon") double lon, Pageable pageable);
}
