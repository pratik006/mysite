package com.prapps.app.rail.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prapps.app.rail.persistence.RegionEntity;

public interface RegionRepo extends JpaRepository<RegionEntity, String>{

	@Query("SELECT r"
			+ " FROM RegionEntity r"
			+ " order by SQRT(POW(69.1 * (r.station.xcoordinates - :lat), 2) +POW(69.1 * (:lon- r.station.ycoordinates) * COS(r.station.xcoordinates / 57.3), 2)) asc")
	List<RegionEntity> getRegion(@Param("lat") double lat, @Param("lon") double lon);
}
