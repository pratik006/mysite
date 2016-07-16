package com.prapps.app.trainapp.dto;

import java.io.Serializable;

public class TrainStation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String arrival;
	private String departure;
	private String stoptime;
	private Integer haltNo;
	private Double distance;
	private Integer day;
	private Integer haltNumber;
	
	private Train train;
	private Station station;
	
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getStoptime() {
		return stoptime;
	}
	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
	}
	public Integer getHaltNo() {
		return haltNo;
	}
	public void setHaltNo(Integer haltNo) {
		this.haltNo = haltNo;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getHaltNumber() {
		return haltNumber;
	}
	public void setHaltNumber(Integer haltNumber) {
		this.haltNumber = haltNumber;
	}
	public Train getTrain() {
		return train;
	}
	public void setTrain(Train train) {
		this.train = train;
	}
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}

}
