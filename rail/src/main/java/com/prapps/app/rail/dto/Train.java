package com.prapps.app.rail.dto;

import java.util.List;

public class Train {

	private Long id;
	private TrainType trainType;
	private List<Route> routes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TrainType getTrainType() {
		return trainType;
	}
	public void setTrainType(TrainType trainType) {
		this.trainType = trainType;
	}
	public List<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
}
