package com.prapps.app.trainapp.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.prapps.app.trainapp.dto.TrainStation;
import com.prapps.app.trainapp.persistence.StationEntity;
import com.prapps.app.trainapp.persistence.TrainRouteEntity;
import com.prapps.app.trainapp.persistence.TrainRoutePk;
import com.prapps.app.trainapp.service.CachedContext;

@Component
public class TrainStationMapper {
	
	private StationMapper stationMapper;
	private CachedContext cachedContext;
	
	@Inject
	public TrainStationMapper(StationMapper stationMapper, CachedContext cachedContext) {
		this.stationMapper = stationMapper;
		this.cachedContext = cachedContext;
	}
	
	public TrainRouteEntity map(TrainStation trainStation, Long trainId) {
		TrainRouteEntity entity = new TrainRouteEntity();
		entity.setArrival(trainStation.getArrival());
		entity.setDeparture(trainStation.getDeparture());
		entity.setStoptime(trainStation.getStoptime());
		entity.setDay(trainStation.getDay());
		entity.setDistance(trainStation.getDistance());
		entity.setHaltNo(trainStation.getHaltNo());
		entity.setStoptime(trainStation.getStoptime());
		StationEntity stationEntity = cachedContext.getStation(trainStation.getStation().getCode());
		entity.setStation(stationEntity);
		entity.setId(new TrainRoutePk(trainId, stationEntity.getId()));
		return entity;
	}
	
	public TrainStation map(TrainRouteEntity entity, boolean mapStation) {
		TrainStation route = new TrainStation();
		route.setArrival(entity.getArrival());
		route.setDeparture(entity.getDeparture());
		route.setStoptime(entity.getStoptime());
		route.setDay(entity.getDay());
		route.setDistance(entity.getDistance());
		route.setHaltNo(entity.getHaltNo());
		route.setStoptime(entity.getStoptime());
		
		if (mapStation) {
			route.setStation(stationMapper.map(entity.getStation()));
		}
		return route;
	}
	
	public List<TrainStation> mapTrainStations(Collection<TrainRouteEntity> entities, boolean mapStation) {
		List<TrainStation> list = new ArrayList<TrainStation>();
		for (TrainRouteEntity entity : entities) {
			list.add(map(entity, mapStation));
		}
		return list;
	}
	
	public List<TrainRouteEntity> mapTrainRoutes(Collection<TrainStation> trainStations, Long trainId) {
		List<TrainRouteEntity> list = new ArrayList<TrainRouteEntity>(trainStations.size());
		for (TrainStation trainStation : trainStations) {
			list.add(map(trainStation,trainId));
		}
		return list;
	}
}
