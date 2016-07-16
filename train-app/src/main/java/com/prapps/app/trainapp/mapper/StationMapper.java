package com.prapps.app.trainapp.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.prapps.app.trainapp.dto.Station;
import com.prapps.app.trainapp.persistence.StationEntity;

@Component
public class StationMapper {
	public Station map(StationEntity entity) {
		Station station = new Station();
		station.setId(entity.getId());
		station.setCode(entity.getCode());
		station.setName(entity.getName());
		return station;
	}
	
	public List<Station> mapStations(Collection<StationEntity> entities) {
		List<Station> trains = new ArrayList<Station>();
		for (StationEntity entity : entities) {
			trains.add(map(entity));
		}
		
		return trains;
	}
	
	public StationEntity map(Station dto) {
		StationEntity station = new StationEntity();
		station.setId(dto.getId());
		station.setCode(dto.getCode());
		station.setName(dto.getName());
		return station;
	}
	
	public List<StationEntity> mapStationDtos(Collection<Station> dtos) {
		List<StationEntity> trains = new ArrayList<StationEntity>();
		for (Station dto : dtos) {
			trains.add(map(dto));
		}
		
		return trains;
	}
}
