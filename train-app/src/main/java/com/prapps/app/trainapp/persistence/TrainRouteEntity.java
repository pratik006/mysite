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
	@Column(name = "stoptime")
	private String stoptime;
	@Column(name = "mon")
	private String mon;
	@Column(name = "tue")
	private String tue;
	@Column(name = "wed")
	private String wed;
	@Column(name = "thu")
	private String thu;
	@Column(name = "fri")
	private String fri;
	@Column(name = "sat")
	private String sat;
	@Column(name = "sun")
	private String sun;
	
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
	public String getStoptime() {
		return stoptime;
	}
	public void setStoptime(String stopTime) {
		this.stoptime = stopTime;
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
}
