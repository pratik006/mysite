package com.prapps.app.trainapp.persistence;

import java.io.Serializable;

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
	
}
