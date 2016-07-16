package com.prapps.app.trainapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.prapps.app.trainapp.dataaccess.ClassRepository;
import com.prapps.app.trainapp.dataaccess.StationRepository;
import com.prapps.app.trainapp.persistence.ClassEntity;
import com.prapps.app.trainapp.persistence.StationEntity;

@Component
public class CachedContext {
	
	private static final Logger LOG = Logger.getLogger(CachedContext.class.getName());

	private Map<String, ClassEntity> classesMap;
	private Map<String,StationEntity> stationMap;
	
	@Inject
	public CachedContext(ClassRepository classRepository, StationRepository stationRepository) {
		classesMap = new HashMap<String, ClassEntity>();
		List<ClassEntity> classes = classRepository.findAll();
		for (ClassEntity entity : classes) {
			classesMap.put(entity.getType(), entity);
		}
		
		stationMap = new HashMap<String, StationEntity>();
		List<StationEntity> stations = stationRepository.findAll();
		for (StationEntity entity : stations) {
			stationMap.put(entity.getCode(), entity);
		}
	}
	
	public ClassEntity getClassIdByType(String type) {
		return classesMap.get(type);
	}
	
	public StationEntity getStation(String code) {
		if (!stationMap.containsKey(code)) {
			LOG.log(Level.SEVERE, "Station code not found: " + code);
		}
		return stationMap.get(code);
	}
}
