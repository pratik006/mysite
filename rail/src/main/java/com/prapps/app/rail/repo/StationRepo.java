package com.prapps.app.rail.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prapps.app.rail.persistence.StationEntity;
import com.prapps.app.rail.type.SuburbanRegionType;

public interface StationRepo extends JpaRepository<StationEntity, Long>{
	int NEAREST_STN_RADIUS = 25;
	int NEAREST_REGION_RADIUS = 200;
	
	List<StationEntity> findByType(SuburbanRegionType type);
	@Query("SELECT s"
			+ " FROM StationEntity s "
			+ " where s.type = :region and SQRT(POW(69.1 * (xcood - :lat), 2) +POW(69.1 * (:lon- ycood) * COS(xcood / 57.3), 2)) < 25 "
			+ " order by SQRT(POW(69.1 * (xcood - :lat), 2) +POW(69.1 * (:lon- ycood) * COS(xcood / 57.3), 2)) asc")
	List<StationEntity> getNearestStations(@Param("region") SuburbanRegionType region, @Param("lat") double lat, @Param("lon") double lon, Pageable pageable);
}
