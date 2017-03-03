package com.prapps.app.rail.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((station == null) ? 0 : station.hashCode());
		result = prime * result + ((train == null) ? 0 : train.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RouteIdEntity other = (RouteIdEntity) obj;
		if (station == null) {
			if (other.station != null)
				return false;
		} else if (!station.equals(other.station))
			return false;
		if (train == null) {
			if (other.train != null)
				return false;
		} else if (!train.equals(other.train))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RouteIdEntity [train=" + train.getId() + ", station=" + station.getId() + "]";
	}
}
