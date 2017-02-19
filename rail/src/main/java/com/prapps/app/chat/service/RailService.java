package com.prapps.app.chat.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prapps.app.chat.repo.StationRepo;
import com.prapps.app.chat.repo.TrainRepo;
import com.prapps.app.rail.dto.SearchType;
import com.prapps.app.rail.dto.Station;
import com.prapps.app.rail.dto.Train;
import com.prapps.app.rail.dto.TrainType;
import com.prapps.app.rail.dto.mapper.StationMapper;
import com.prapps.app.rail.dto.mapper.TrainMapper;
import com.prapps.app.rail.entity.RouteEntity;
import com.prapps.app.rail.entity.TrainEntity;

@Service
public class RailService {

	private StationRepo stationRepo;
	private TrainRepo trainRepo;
	private StationMapper stationMapper;
	private TrainMapper trainMapper;
	
	@Autowired
	public RailService(StationRepo stationRepo, StationMapper stationMapper, TrainRepo trainRepo, TrainMapper trainMapper) {
		this.stationRepo = stationRepo;
		this.stationMapper = stationMapper;
		this.trainRepo = trainRepo;
		this.trainMapper = trainMapper;
	}
	
	public List<Station> getStations(SearchType searchType) {
		if (SearchType.HYD_MMTS == searchType) {
			return stationMapper.map(stationRepo.findByType(searchType.getType()));
		}
		
		return null;
	}
	
	public List<Train> findTrains(String from, String to, TrainType trainType, int page, int size) {
		Pageable request = new PageRequest(page - 1, size, Sort.Direction.ASC, "departure");
		List<TrainEntity> trainEntities = trainRepo.findTrains(from, to, trainType, request);
		for (TrainEntity entity : trainEntities) {
			List<RouteEntity> routes = new ArrayList<RouteEntity>(2);
			Iterator<RouteEntity> itr = entity.getRoutes().iterator();
			while(itr.hasNext()) {
				RouteEntity en = itr.next();
				if (from.equals(en.getId().getStation().getCode())) {
					routes.add(0, en);
				} else if (to.equals(en.getId().getStation().getCode())) {
					routes.add(en);
				}
			}
			entity.setRoutes(routes);
		}
		return trainMapper.map(trainEntities);
	}
}
