package com.prapps.app.trainapp.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.prapps.app.trainapp.dto.Train;
import com.prapps.app.trainapp.persistence.ClassEntity;
import com.prapps.app.trainapp.persistence.TrainEntity;
import com.prapps.app.trainapp.service.CachedContext;

@Component
public class TrainMapper {

	@Inject private TrainStationMapper trainStationMapper;
	@Inject private CachedContext cachedCtx;
	
	public Train map(TrainEntity entity, boolean mapRoute) {
		Train train = new Train();
		train.setId(entity.getId());
		train.setName(entity.getName());
		train.setType(entity.getType());
		train.setAdvanceReservationPeriod(entity.getAdvanceReservationPeriod());
		train.setAvgSpeed(entity.getAvgSpeed());
		train.setFareType(entity.getFareType());
		train.setGauge(entity.getGauge());
		train.setInclFoodCost(entity.getInclFoodCost());
		train.setOwner(entity.getOwner());
		train.setPairTrain(entity.getPairTrain());
		train.setPantry(entity.getPantry());
		train.setRakeShare(entity.getRakeShare());
		train.setTotalTravelTime(entity.getTotalTravelTime());
		train.setRundays(entity.getRundays());
		train.setPairTrainId(entity.getPairTrainId());
		
		train.setMon(entity.getMon());
		train.setTue(entity.getTue());
		train.setWed(entity.getWed());
		train.setThu(entity.getThu());
		train.setFri(entity.getFri());
		train.setSat(entity.getSat());
		train.setSun(entity.getSun());
		
		train.setClasses(mapReservationClass(entity.getClasses()));
		
		if (mapRoute) {
			train.setRoutes(trainStationMapper.mapTrainStations(entity.getRoutes(), true));
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
	
	public List<String> mapReservationClass(List<ClassEntity> entities) {
		List<String> list = new ArrayList<String>();
		
		for (ClassEntity entity : entities) {
			list.add(entity.getType());
		}
		
		return list;
	}
	
	public List<ClassEntity> mapClasses(List<String> classes) {
		List<ClassEntity> list = new ArrayList<ClassEntity>(classes.size());
		for (String type : classes) {
			list.add(cachedCtx.getClassIdByType(type));
		}
		return list;
	}
	
	public TrainEntity map(Train train) {
		TrainEntity entity = new TrainEntity();
		entity.setId(train.getId());
		entity.setName(train.getName());
		entity.setType(train.getType());
		entity.setAdvanceReservationPeriod(train.getAdvanceReservationPeriod());
		entity.setAvgSpeed(train.getAvgSpeed());
		entity.setFareType(train.getFareType());
		entity.setGauge(train.getGauge());
		entity.setInclFoodCost(train.getInclFoodCost());
		entity.setOwner(train.getOwner());
		entity.setPairTrain(train.getPairTrain());
		entity.setPantry(train.getPantry());
		entity.setRakeShare(train.getRakeShare());
		entity.setTotalTravelTime(train.getTotalTravelTime());
		entity.setRundays(train.getRundays());
		entity.setPairTrainId(train.getPairTrainId());
		
		entity.setMon(train.getMon());
		entity.setTue(train.getTue());
		entity.setWed(train.getWed());
		entity.setThu(train.getThu());
		entity.setFri(train.getFri());
		entity.setSat(train.getSat());
		entity.setSun(train.getSun());
		
		entity.setClasses(mapClasses(train.getClasses()));
		entity.setRoutes(trainStationMapper.mapTrainRoutes(train.getRoutes(), train.getId()));
		return entity;
	}
	
	public List<TrainEntity> mapTrainDtos(Collection<Train> dtos) {
		List<TrainEntity> trains = new ArrayList<TrainEntity>();
		for (Train dto : dtos) {
			trains.add(map(dto));
		}
		
		return trains;
	}
	
}
