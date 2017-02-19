package com.prapps.app.rail.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "route", schema = "trainapp")
public class RouteEntity {

	@EmbeddedId
	private RouteIdEntity id;
	@Column(name = "arrival")
	private String arrival;
	@Column(name = "departure")
	private String departure;
	@Column(name = "stoptime")
	private String stopTime;
	@Column(name = "day")
	private Integer day;
	@Column(name = "distance")
	private Double dist;
	@Column(name = "halt_no")
	private Integer halt;
	
	public RouteIdEntity getId() {
		return id;
	}
	public void setId(RouteIdEntity id) {
		this.id = id;
	}
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
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Double getDist() {
		return dist;
	}
	public void setDist(Double dist) {
		this.dist = dist;
	}
	public Integer getHalt() {
		return halt;
	}
	public void setHalt(Integer halt) {
		this.halt = halt;
	}
}
