package com.prapps.app.rail.dto.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prapps.app.rail.dto.Train;
import com.prapps.app.rail.entity.TrainEntity;
import com.prapps.app.rail.type.RunDayType;

@Component
public class TrainMapper {

	private RouteMapper routeMapper;
	
	@Autowired
	public TrainMapper(RouteMapper routeMapper) {
		this.routeMapper = routeMapper;
	}
	
	public Train map(TrainEntity entity) {
		Train train = new Train();
		train.setId(entity.getId());
		train.setTrainType(entity.getType());
		train.setName(entity.getName());
		List<RunDayType> runDayTypes = new ArrayList<RunDayType>();
		for (String runday : entity.getRundays().split(" ")) {
			runDayTypes.add(RunDayType.getByRunDay(runday));
		}
		train.setRunDayTypes(runDayTypes);
		train.setRoutes(routeMapper.map(entity.getRoutes()));
		return train;
	}
	
	public List<Train> map(Collection<TrainEntity> entities) {
		List<Train> trains = new ArrayList<Train>(entities.size());
		for (TrainEntity entity : entities) {
			trains.add(map(entity));
		}
		
		return trains;
	} 
}
