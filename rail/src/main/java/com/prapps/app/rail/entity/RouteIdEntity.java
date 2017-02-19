package com.prapps.app.rail.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class RouteIdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "train_id")
	private TrainEntity train;
	@ManyToOne
	@JoinColumn(name = "station_id")
	private StationEntity station;
	
	public TrainEntity getTrain() {
		return train;
	}
	public void setTrain(TrainEntity train) {
		this.train = train;
	}
	public StationEntity getStation() {
		return station;
	}
	public void setStation(StationEntity stationEntity) {
		this.station = stationEntity;
	}
}
