package com.prapps.app.rail.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prapps.app.rail.dto.Region;
import com.prapps.app.rail.dto.ResponseDetail;
import com.prapps.app.rail.dto.Station;
import com.prapps.app.rail.dto.Train;
import com.prapps.app.rail.dto.TrainType;
import com.prapps.app.rail.dto.comparator.RouteComparator;
import com.prapps.app.rail.dto.mapper.RegionMapper;
import com.prapps.app.rail.dto.mapper.RouteMapper;
import com.prapps.app.rail.dto.mapper.StationMapper;
import com.prapps.app.rail.dto.mapper.TrainMapper;
import com.prapps.app.rail.persistence.RegionEntity;
import com.prapps.app.rail.persistence.RouteEntity;
import com.prapps.app.rail.persistence.StationEntity;
import com.prapps.app.rail.persistence.TrainEntity;
import com.prapps.app.rail.repo.RegionRepo;
import com.prapps.app.rail.repo.StationRepo;
import com.prapps.app.rail.repo.TrainRepo;
import com.prapps.app.rail.type.RunDayType;
import com.prapps.app.rail.type.SuburbanRegionType;

@Service @Transactional(readOnly = true)
public class RailService {
	private Logger LOG = LoggerFactory.getLogger(RailService.class);

	private StationRepo stationRepo;
	private RegionRepo regionRepo;
	private TrainRepo trainRepo;
	private StationMapper stationMapper;
	private TrainMapper trainMapper;
	private RouteMapper routeMapper;
	private RegionMapper regionMapper;

	@Autowired
	public RailService(StationRepo stationRepo, RegionRepo regionRepo, StationMapper stationMapper, TrainRepo trainRepo, TrainMapper trainMapper, RouteMapper routeMapper, RegionMapper regionMapper) {
		this.stationRepo = stationRepo;
		this.regionRepo = regionRepo;
		this.stationMapper = stationMapper;
		this.trainRepo = trainRepo;
		this.trainMapper = trainMapper;
		this.routeMapper = routeMapper;
		this.regionMapper = regionMapper;
	}

	public List<Station> getStations(SuburbanRegionType regionType) {
		if (SuburbanRegionType.ALL != regionType) {
			return stationMapper.map(stationRepo.findByType(regionType));
		}

		return stationMapper.map(stationRepo.findAll());
	}

	public Page<Train> findTrains(String from, String to, Calendar startTime, Calendar endTime, List<TrainType> trainTypes, int page, int size, ResponseDetail detail) {
		Pageable request = new PageRequest(page - 1, size, Sort.Direction.ASC, "departure");

		Page<TrainEntity> trainEntities = null;
		if (startTime != null && endTime != null) {
			RunDayType runDayType = RunDayType.getByDayOfWeek(startTime.get(Calendar.DAY_OF_WEEK));
			int startHour = startTime.get(Calendar.HOUR_OF_DAY);
			int endHour = endTime.get(Calendar.HOUR_OF_DAY);
			if (endHour > startHour) {
				String departureStart = String.format("%02d", startHour) + "." + String.format("%02d", startTime.get(Calendar.MINUTE));
				String departureEnd = String.format("%02d", endTime.get(Calendar.HOUR_OF_DAY)) + "." + String.format("%02d", endTime.get(Calendar.MINUTE));
				trainEntities = trainRepo.findTrains(from, to, departureStart, departureEnd, runDayType.getRunDay(), trainTypes, request);
			} else {
				//time goes to next day
				String departureStart = String.format("%02d", startHour) + "." + String.format("%02d", startTime.get(Calendar.MINUTE));
				String departureEnd = "23.59";
				Page<TrainEntity> resultPage1 = trainRepo.findTrains(from, to, departureStart, departureEnd, runDayType.getRunDay(), trainTypes, request);

				departureStart = "00.00";
				departureEnd = String.format("%02d", endTime.get(Calendar.HOUR_OF_DAY)) + "." + String.format("%02d", endTime.get(Calendar.MINUTE));
				Page<TrainEntity> resultPage2 = trainRepo.findTrains(from, to, departureStart, departureEnd, runDayType.getRunDay(), trainTypes, request);
				List<TrainEntity> consolidatedList = new ArrayList<TrainEntity>(resultPage1.getContent());
				consolidatedList.addAll(resultPage2.getContent());
				trainEntities = new PageImpl<TrainEntity>(consolidatedList);
			}

		} else {
			trainEntities = trainRepo.findTrains(from, to, trainTypes, request);
		}


		Set<RouteEntity> routes;
		for (TrainEntity entity : trainEntities) {
			Iterator<RouteEntity> itr = entity.getRoutes().iterator();
			if (detail == ResponseDetail.ALL) {
				routes = new TreeSet<RouteEntity>(RouteComparator.INSTANCE);
				while(itr.hasNext()) {
					RouteEntity en = itr.next();
					routes.add(en);
				}
			} else {
				routes = new LinkedHashSet<RouteEntity>(2);
				while(itr.hasNext()) {
					RouteEntity en = itr.next();
					if (from.equals(en.getId().getStation().getCode())) {
						routes.add(en);
					} else if (to.equals(en.getId().getStation().getCode())) {
						routes.add(en);
					}
				}
			}

			entity.setRoutes(routes);
		}
		List<Train> trains = trainMapper.map(trainEntities);
		return new PageImpl<Train>(trains, trainEntities.nextPageable() ,trainEntities.getTotalElements());
	}

	public List<Station> getNearestStations(SuburbanRegionType regionType, double lat, double lon) {
		Pageable pageable = new PageRequest(0, 3);
		List<StationEntity> stationEntities = stationRepo.getNearestStations(regionType, lat, lon, pageable);
		return stationMapper.map(stationEntities);
	}

	public Set<Region> getNearestRegion(double lat, double lon) {
		List<RegionEntity> stationEntities = regionRepo.getRegion(lat, lon);
		return regionMapper.map(stationEntities);
	}

	public Set<Region> getAllRegions() {
		return regionMapper.map(regionRepo.findAll());
	}

	@Transactional
	public synchronized void updateTrainRoute(Train train) {
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
