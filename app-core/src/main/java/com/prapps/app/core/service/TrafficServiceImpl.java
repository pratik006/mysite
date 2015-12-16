package com.prapps.app.core.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.prapps.app.core.api.TrafficService;
import com.prapps.app.core.dataaccess.TrafficRepository;
import com.prapps.app.core.persistence.TrafficEntity;
import com.prapps.app.core.util.time.TimeUtil;

@Service
public class TrafficServiceImpl implements TrafficService {

	private TrafficRepository trafficRepository;
	private TimeUtil timeUtil;
	
	@Inject
	public TrafficServiceImpl(TrafficRepository trafficRepository, TimeUtil timeUtil) {
		this.trafficRepository = trafficRepository;
		this.timeUtil = timeUtil;
	}
	@Override
	public void logTraffic(String remoteAddr, String uri) {
		if ("7e5b1ce27f15ae829a6bf6e9e14f6931f5f3b218".equals(remoteAddr)) {
			return;
		}
		
		TrafficEntity entity = new TrafficEntity();
		entity.setFrom(remoteAddr);
		entity.setUri(uri);
		entity.setTime(timeUtil.getCurrentTime());
		trafficRepository.save(entity);
	}
}
