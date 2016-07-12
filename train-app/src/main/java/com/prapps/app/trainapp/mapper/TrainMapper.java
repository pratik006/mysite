package com.prapps.app.trainapp.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.prapps.app.trainapp.dto.Station;
import com.prapps.app.trainapp.dto.Train;
import com.prapps.app.trainapp.dto.TrainStation;
import com.prapps.app.trainapp.persistence.StationEntity;
import com.prapps.app.trainapp.persistence.TrainEntity;
import com.prapps.app.trainapp.persistence.TrainRouteEntity;

@Component
public class TrainMapper {

	public Train map(TrainEntity entity, boolean mapRoute) {
		Train train = new Train();
		train.setId(entity.getId());
		train.setName(entity.getName());
		if (mapRoute) {
			train.setRoutes(mapTrainRoutes(entity.getRoutes(), true));
		}
		return train;
	}
	
	public List<Train> mapTrains(Collection<TrainEntity> entities, boolean mapRoute) {
		List<Train> trains = new ArrayList<Train>();
		for (TrainEntity entity : entities) {
			trains.add(map(entity, mapRoute));
		}
		
		return trains;
	}
	
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
	
	public TrainEntity map(Train dto) {
		TrainEntity train = new TrainEntity();
		train.setId(dto.getId());
		train.setName(dto.getName());
		return train;
	}
	
	public List<TrainEntity> mapTrainDtos(Collection<Train> dtos) {
		List<TrainEntity> trains = new ArrayList<TrainEntity>();
		for (Train dto : dtos) {
			trains.add(map(dto));
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
	
	public TrainRouteEntity map(TrainStation trainStation) {
		TrainRouteEntity entity = new TrainRouteEntity();
		entity.setArrival(trainStation.getArrival());
		entity.setDeparture(trainStation.getDeparture());
		entity.setStoptime(trainStation.getStoptime());
		entity.setMon(trainStation.getMon());
		entity.setTue(trainStation.getTue());
		entity.setWed(trainStation.getWed());
		entity.setThu(trainStation.getThu());
		entity.setFri(trainStation.getFri());
		entity.setSat(trainStation.getSat());
		entity.setSun(trainStation.getSun());
		return entity;
	}
	
	public TrainStation map(TrainRouteEntity entity, boolean mapStation) {
		TrainStation route = new TrainStation();
		route.setArrival(entity.getArrival());
		route.setDeparture(entity.getDeparture());
		route.setStoptime(entity.getStoptime());
		route.setMon(entity.getMon());
		route.setTue(entity.getTue());
		route.setWed(entity.getWed());
		route.setThu(entity.getThu());
		route.setFri(entity.getFri());
		route.setSat(entity.getSat());
		route.setSun(entity.getSun());
		if (mapStation) {
			route.setStation(map(entity.getStation()));
		}
		return route;
	}
	
	public List<TrainStation> mapTrainRoutes(Collection<TrainRouteEntity> entities, boolean mapStation) {
		List<TrainStation> list = new ArrayList<TrainStation>();
		for (TrainRouteEntity entity : entities) {
			list.add(map(entity, mapStation));
		}
		return list;
	}
}
