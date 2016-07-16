package com.prapps.app.trainapp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.prapps.app.trainapp.persistence.TrainEntity;

public class Train implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String type;
	
	private String rundays;
	private String pantry;
	private String inclFoodCost;
	private Integer totalTravelTime;
	private Integer avgSpeed;
	private Integer advanceReservationPeriod;
	private String owner;
	private String rakeShare;
	private String fareType;
	private String gauge;
	private Long pairTrainId;
	
	
	private String mon;
	private String tue;
	private String wed;
	private String thu;
	private String fri;
	private String sat;
	private String sun;
	
	private TrainEntity pairTrain;
	
	private List<TrainStation> routes;
	private List<String> classes;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRundays() {
		return rundays;
	}

	public void setRundays(String rundays) {
		this.rundays = rundays;
	}

	public String getPantry() {
		return pantry;
	}

	public void setPantry(String pantry) {
		this.pantry = pantry;
	}

	public String getInclFoodCost() {
		return inclFoodCost;
	}

	public void setInclFoodCost(String inclFoodCost) {
		this.inclFoodCost = inclFoodCost;
	}

	public Integer getTotalTravelTime() {
		return totalTravelTime;
	}

	public void setTotalTravelTime(Integer totalTravelTime) {
		this.totalTravelTime = totalTravelTime;
	}

	public Integer getAvgSpeed() {
		return avgSpeed;
	}

	public void setAvgSpeed(Integer avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public Integer getAdvanceReservationPeriod() {
		return advanceReservationPeriod;
	}

	public void setAdvanceReservationPeriod(Integer advanceReservationPeriod) {
		this.advanceReservationPeriod = advanceReservationPeriod;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getRakeShare() {
		return rakeShare;
	}

	public void setRakeShare(String rakeShare) {
		this.rakeShare = rakeShare;
	}

	public String getFareType() {
		return fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}

	public String getGauge() {
		return gauge;
	}

	public void setGauge(String gauge) {
		this.gauge = gauge;
	}

	public Long getPairTrainId() {
		return pairTrainId;
	}

	public void setPairTrainId(Long pairTrainId) {
		this.pairTrainId = pairTrainId;
	}

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}

	public String getTue() {
		return tue;
	}

	public void setTue(String tue) {
		this.tue = tue;
	}

	public String getWed() {
		return wed;
	}

	public void setWed(String wed) {
		this.wed = wed;
	}

	public String getThu() {
		return thu;
	}

	public void setThu(String thu) {
		this.thu = thu;
	}

	public String getFri() {
		return fri;
	}

	public void setFri(String fri) {
		this.fri = fri;
	}

	public String getSat() {
		return sat;
	}

	public void setSat(String sat) {
		this.sat = sat;
	}

	public String getSun() {
		return sun;
	}

	public void setSun(String sun) {
		this.sun = sun;
	}

	public TrainEntity getPairTrain() {
		return pairTrain;
	}

	public void setPairTrain(TrainEntity pairTrain) {
		this.pairTrain = pairTrain;
	}

	public List<TrainStation> getRoutes() {
		if (routes == null) {
			routes = new ArrayList<TrainStation>();
		}
		return routes;
	}

	public void setRoutes(List<TrainStation> routes) {
		this.routes = routes;
	}

	public List<String> getClasses() {
		if (classes == null) {
			classes = new ArrayList<String>();
		}
		return classes;
	}

	public void setClasses(List<String> classes) {
		this.classes = classes;
	}
}
