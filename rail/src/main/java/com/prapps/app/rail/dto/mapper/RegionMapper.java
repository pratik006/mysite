package com.prapps.app.rail.dto.mapper;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.prapps.app.rail.dto.Region;
import com.prapps.app.rail.entity.RegionEntity;

@Component
public class RegionMapper {
	public Region map(RegionEntity entity) {
		Region region = new Region();
		region.setCode(entity.getCode());
		region.setName(entity.getName());
		return region;
	}
	
	public Set<Region> map(List<RegionEntity> entities) {
		Set<Region> regions = new LinkedHashSet<Region>(entities.size());
		for (RegionEntity entity : entities) {
			regions.add(map(entity));
		}
		
		return regions;
	}
}
