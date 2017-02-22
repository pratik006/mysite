package com.prapps.app.rail.dto;

import java.util.List;

import com.prapps.app.chat.type.RunDayType;

public class Train {

	private Long id;
	private String name;
	private TrainType trainType;
	private List<RunDayType> runDayType;
	private List<Route> routes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TrainType getTrainType() {
		return trainType;
	}
	public void setTrainType(TrainType trainType) {
		this.trainType = trainType;
	}
	public List<RunDayType> getRunDayType() {
		return runDayType;
	}
	public void setRunDayType(List<RunDayType> runDayType) {
		this.runDayType = runDayType;
	}
	public List<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
}
