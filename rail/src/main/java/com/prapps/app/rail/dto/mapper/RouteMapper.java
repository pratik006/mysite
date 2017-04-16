package com.prapps.app.rail.dto.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.prapps.app.rail.dto.Route;
import com.prapps.app.rail.persistence.RouteEntity;
import com.prapps.app.rail.persistence.RouteIdEntity;
import com.prapps.app.rail.persistence.StationEntity;
import com.prapps.app.rail.persistence.TrainEntity;

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
		route.setStationCode(entity.getId().getStation().getCode());
		route.setStationName(entity.getId().getStation().getName());
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
	
	public RouteEntity map(Route route, TrainEntity train) {
		RouteEntity routeEntity = new RouteEntity();
		routeEntity.setArrival(route.getArrival());
		routeEntity.setDay(route.getDay());
		routeEntity.setDeparture(route.getDeparture());
		routeEntity.setDist(route.getDist());
		routeEntity.setHalt(route.getHalt());
		routeEntity.setStopTime(route.getStopTime());
		RouteIdEntity id = new RouteIdEntity();
		StationEntity stationEntity = new StationEntity();
		stationEntity.setId(route.getStationId());
		id.setStation(stationEntity);
		id.setTrain(train);
		routeEntity.setId(id);
		return routeEntity;
	}
	
	public List<RouteEntity> mapRoutes(Collection<Route> routes, TrainEntity trainEntity) {
		List<RouteEntity> entities = new ArrayList<RouteEntity>(routes.size());
		for (Route route : routes) {
			entities.add(map(route, trainEntity));
		}
		
		return entities;
	}

}
