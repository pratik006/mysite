package com.prapps.app.rail.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prapps.app.rail.dto.SearchType;
import com.prapps.app.rail.dto.Station;
import com.prapps.app.rail.dto.Train;
import com.prapps.app.rail.dto.TrainType;
import com.prapps.app.rail.dto.mapper.RouteMapper;
import com.prapps.app.rail.dto.mapper.StationMapper;
import com.prapps.app.rail.dto.mapper.TrainMapper;
import com.prapps.app.rail.entity.RouteEntity;
import com.prapps.app.rail.entity.StationEntity;
import com.prapps.app.rail.entity.TrainEntity;
import com.prapps.app.rail.repo.StationRepo;
import com.prapps.app.rail.repo.TrainRepo;
import com.prapps.app.rail.type.RunDayType;

@Service
public class RailService {
	private Logger LOG = LoggerFactory.getLogger(RailService.class);
	
	private StationRepo stationRepo;
	private TrainRepo trainRepo;
	private StationMapper stationMapper;
	private TrainMapper trainMapper;
	private RouteMapper routeMapper;
	
	@Autowired
	public RailService(StationRepo stationRepo, StationMapper stationMapper, TrainRepo trainRepo, TrainMapper trainMapper, RouteMapper routeMapper) {
		this.stationRepo = stationRepo;
		this.stationMapper = stationMapper;
		this.trainRepo = trainRepo;
		this.trainMapper = trainMapper;
		this.routeMapper = routeMapper;
	}
	
	public List<Station> getStations(SearchType searchType) {
		if (SearchType.HYD_MMTS == searchType) {
			return stationMapper.map(stationRepo.findByType(searchType.getType()));
		}
		
		return stationMapper.map(stationRepo.findAll());
	}
	
	public List<Train> findTrains(String from, String to, Calendar startTime, Calendar endTime, TrainType trainType, int page, int size) {
		Pageable request = new PageRequest(page - 1, size, Sort.Direction.ASC, "departure");
		
		List<TrainEntity> trainEntities = null;
		if (startTime != null && endTime != null) {
			RunDayType runDayType = RunDayType.getByDayOfWeek(startTime.get(Calendar.DAY_OF_WEEK));
			String departureStart = String.format("%02d", startTime.get(Calendar.HOUR_OF_DAY)) + "." + String.format("%02d", startTime.get(Calendar.MINUTE));
			String departureEnd = String.format("%02d", endTime.get(Calendar.HOUR_OF_DAY)) + "." + String.format("%02d", endTime.get(Calendar.MINUTE));
			trainEntities = trainRepo.findTrains(from, to, departureStart, departureEnd, runDayType.getRunDay(), trainType, request);
		} else {
			trainEntities = trainRepo.findTrains(from, to, trainType, request);
		}
		
		
		
		for (TrainEntity entity : trainEntities) {
			Set<RouteEntity> routes = new LinkedHashSet<RouteEntity>(2);
			Iterator<RouteEntity> itr = entity.getRoutes().iterator();
			while(itr.hasNext()) {
				RouteEntity en = itr.next();
				if (from.equals(en.getId().getStation().getCode())) {
					routes.add(en);
				} else if (to.equals(en.getId().getStation().getCode())) {
					routes.add(en);
				}
			}
			entity.setRoutes(routes);
		}
		return trainMapper.map(trainEntities);
	}
	
	public List<Station> getNearestStations(float lat, float lon) {
		Pageable pageable = new PageRequest(0, 3);
		List<StationEntity> stationEntities = stationRepo.getNearestStations(lat, lon, pageable);
		return stationMapper.map(stationEntities);
	}
	
	@Transactional
	public void updateTrainRoute(Train train) {
		TrainEntity trainEntity = null;
		if (trainRepo.exists(train.getId())) {
			trainEntity = trainRepo.findOne(train.getId());
			if (train.getRoutes().size() > trainEntity.getRoutes().size()) {
				Collection<RouteEntity> routeEntities = routeMapper.mapRoutes(train.getRoutes(), trainEntity);
				for (RouteEntity entity : routeEntities) {
					StationEntity stn = stationRepo.findOne(entity.getId().getStation().getId());
					entity.getId().setStation(stn);
				}
				trainEntity.getRoutes().addAll(routeEntities);
				trainEntity = trainRepo.save(trainEntity);
			}else {
				LOG.trace("route already added for "+train.getName());
			}
		}
	}
}
