package com.prapps.app.rail.dto.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.prapps.app.rail.dto.Route;
import com.prapps.app.rail.entity.RouteEntity;

@Component
public class RouteMapper {
	
	public Route map(RouteEntity entity) {
		Route route = new Route();
		route.setArrival(entity.getArrival());
		route.setDay(entity.getDay());
		route.setDeparture(entity.getDeparture());
		route.setDist(entity.getDist());
		route.setHalt(entity.getHalt());
		route.setStationId(entity.getId().getStation().getId());
		route.setStopTime(entity.getStopTime());
		route.setTrainId(entity.getId().getTrain().getId());
		return route;
	}
	
	public List<Route> map(Collection<RouteEntity> entities) {
		List<Route> stations = new ArrayList<Route>(entities.size());
		for (RouteEntity entity : entities) {
			stations.add(map(entity));
		}
		
		return stations;
	}

}
