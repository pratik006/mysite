package com.prapps.app.rail.dto;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Station {
	private long id;
	private String code;
	private String name;
	@JsonIgnore
	private List<Station> nextStation;
	@JsonIgnore
	private List<Integer> trains;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<Station> getNextStation() {
		return nextStation;
	}
	public void setNextStation(List<Station> nextStation) {
		this.nextStation = nextStation;
	}
	public Collection<Integer> getTrains() {
		return trains;
	}
	public void setTrains(List<Integer> trains) {
		this.trains = trains;
	}
	@Override
	public String toString() {
		return "Station [id=" + id + ", code=" + code + ", name=" + name + "]";
	}
}
