package com.prapps.app.rail.dto.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prapps.app.rail.dto.Train;
import com.prapps.app.rail.entity.TrainEntity;

@Component
public class TrainMapper {

	private RouteMapper routeMapper;
	
	@Autowired
	public TrainMapper(RouteMapper routeMapper) {
		this.routeMapper = routeMapper;
	}
	
	public Train map(TrainEntity entity) {
		Train station = new Train();
		station.setId(entity.getId());
		station.setTrainType(entity.getType());
		station.setRoutes(routeMapper.map(entity.getRoutes()));
		return station;
	}
	
	public List<Train> map(Collection<TrainEntity> entities) {
		List<Train> stations = new ArrayList<Train>(entities.size());
		for (TrainEntity entity : entities) {
			stations.add(map(entity));
		}
		
		return stations;
	} 
}
