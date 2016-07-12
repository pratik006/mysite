package com.prapps.app.trainapp.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.prapps.app.trainapp.dataaccess.StationRepository;
import com.prapps.app.trainapp.dataaccess.TrainRepository;
import com.prapps.app.trainapp.dataaccess.TrainRouteRepository;
import com.prapps.app.trainapp.dto.SearchRequest;
import com.prapps.app.trainapp.dto.Station;
import com.prapps.app.trainapp.dto.Train;
import com.prapps.app.trainapp.dto.TrainStation;
import com.prapps.app.trainapp.mapper.TrainMapper;
import com.prapps.app.trainapp.persistence.StationEntity;
import com.prapps.app.trainapp.persistence.TrainEntity;
import com.prapps.app.trainapp.persistence.TrainRouteEntity;
import com.prapps.app.trainapp.persistence.TrainRoutePk;

@Service
public class TrainService {
	
	private StationRepository stationRepository;
	private TrainRouteRepository trainRouteRepository;
	private TrainRepository trainRepository;
	private TrainMapper trainMapper;
	@PersistenceContext
	 private EntityManager em;
	
	private Session getSession() {
		return em.unwrap(Session.class);
	}
	
	@Inject
	public TrainService(StationRepository stationRepository, TrainRouteRepository trainRouteRepository, 
			TrainRepository trainRepository, TrainMapper trainMapper) {
		this.stationRepository = stationRepository;
		this.trainRouteRepository = trainRouteRepository;
		this.trainRepository = trainRepository;
		this.trainMapper = trainMapper;
	}

	public List<Station> getMatchingStations(String match) {
		return trainMapper.mapStations(stationRepository.findByNameContaining(match));
	}
	
	public List<Train> searchTrains(SearchRequest request) {
		List<Long> entities = getSession().createCriteria(TrainRouteEntity.class)
		.createAlias("station", "station").add(Restrictions.eq("station.name", request.getFrom()))
		.setProjection(Projections.projectionList().add(Projections.property("id.trainId")))
		.list();
		
		List<TrainEntity> trainEntities = getSession().createCriteria(TrainEntity.class)
				.createAlias("routes", "routes")
				.setFetchMode("routes", FetchMode.JOIN)
				.createAlias("routes.station", "station")
				.setFetchMode("station", FetchMode.JOIN)
			.add(Restrictions.eq("station.name", request.getTo()))
			.add(Restrictions.in("routes.id.trainId", entities)).list();
		
		return trainMapper.mapTrains(trainEntities, true);
	}
	
	public void updateTrainRoute(TrainStation trainStation) {
		TrainEntity train = null;
		if (trainRepository.exists(trainStation.getTrain().getId())) {
			train = trainRepository.findById(trainStation.getTrain().getId());
		} else {
			train = new TrainEntity();
			train.setId(trainStation.getTrain().getId());
			train.setName(trainStation.getTrain().getName());
			train = trainRepository.save(train);
		}
		
		StationEntity station = stationRepository.findByCode(trainStation.getStation().getCode());
		if (station == null) {
			station = new StationEntity();
			station.setCode(trainStation.getStation().getCode());
			station.setName(trainStation.getStation().getName());
			station = stationRepository.save(station);
		}
		
		TrainRouteEntity entity = trainMapper.map(trainStation);
		TrainRoutePk id = new TrainRoutePk(train.getId(), station.getId());
		if (!trainRouteRepository.exists(id)) {
			entity.setId(id);
			entity.setTrain(train);
			entity.setStation(station);
			trainRouteRepository.save(entity);
		}
	}
}
