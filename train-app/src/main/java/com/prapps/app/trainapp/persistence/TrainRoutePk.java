package com.prapps.app.trainapp.persistence;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TrainRoutePk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "train_id")
	private Long trainId;
	@Column(name = "station_id")
	private Long stationId;

	public TrainRoutePk() {
		// TODO Auto-generated constructor stub
	}
	
	public TrainRoutePk(Long trainId, Long stationId) {
		this.trainId = trainId;
		this.stationId = stationId;
	}

	public Long getTrainId() {
		return trainId;
	}

	public void setTrainId(Long trainId) {
		this.trainId = trainId;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(trainId, stationId);
	}
	
	@Override
	public boolean equals(Object otherObj) {
		if (otherObj instanceof TrainRoutePk) {
			TrainRoutePk other = (TrainRoutePk) otherObj;
			return trainId == other.getTrainId() && stationId == other.getStationId();
		}
		
		return false;
	}
}