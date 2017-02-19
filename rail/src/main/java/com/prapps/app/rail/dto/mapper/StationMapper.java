package com.prapps.app.rail.dto.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.prapps.app.rail.dto.Station;
import com.prapps.app.rail.entity.StationEntity;

@Component
public class StationMapper {
	public Station map(StationEntity entity) {
		Station station = new Station();
		station.setCode(entity.getCode());
		station.setName(entity.getName());
		return station;
	}
	
	public List<Station> map(Collection<StationEntity> entities) {
		List<Station> stations = new ArrayList<Station>(entities.size());
		for (StationEntity entity : entities) {
			stations.add(map(entity));
		}
		
		return stations;
	}
}
