package com.prapps.app.trainapp.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "route", schema = "trainapp")
public class TrainRouteEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TrainRoutePk id;
	
	@Column(name = "arrival")
	private String arrival;
	@Column(name = "departure")
	private String departure;
	@Column(name = "halt_no")
	private Integer haltNo;
	@Column(name = "stoptime")
	private String stoptime;
	@Column(name = "day")
	private Integer day;
	@Column(name = "distance")
	private Double distance;
	
	@ManyToOne
	@JoinColumn(name = "train_id", insertable = false, updatable = false)
	private TrainEntity train;
	
	@ManyToOne
	@JoinColumn(name = "station_id", insertable = false, updatable = false)
	private StationEntity station;

	public TrainRoutePk getId() {
		return id;
	}
	
	public void setId(TrainRoutePk id) {
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

	public Integer getHaltNo() {
		return haltNo;
	}

	public void setHaltNo(Integer haltNo) {
		this.haltNo = haltNo;
	}

	public String getStoptime() {
		return stoptime;
	}

	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public TrainEntity getTrain() {
		return train;
	}

	public void setTrain(TrainEntity train) {
		this.train = train;
	}

	public StationEntity getStation() {
		return station;
	}

	public void setStation(StationEntity station) {
		this.station = station;
	}
}
