package com.prapps.app.rail.dto;

import java.util.ArrayList;
import java.util.List;

import com.prapps.app.rail.type.RunDayType;

public class Train {

	private Long id;
	private String name;
	private TrainType trainType;
	private List<RunDayType> runDayTypes;
	private List<Route> routes;
	private List<String> trainClasses;
	
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
	public List<RunDayType> getRunDayTypes() {
		return runDayTypes;
	}
	public void setRunDayTypes(List<RunDayType> runDayType) {
		this.runDayTypes = runDayType;
	}
	public void setDays(List<String> days) {
		List<RunDayType> runDayTypes = new ArrayList<RunDayType>(days.size());
		for (String day : days) {
			runDayTypes.add(RunDayType.getByRunDay(day));
		}
		this.runDayTypes = runDayTypes;
	}
	public List<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	public List<String> getTrainClasses() {
		return trainClasses;
	}
	public void setTrainClasses(List<String> trainClasses) {
		this.trainClasses = trainClasses;
	}
}
