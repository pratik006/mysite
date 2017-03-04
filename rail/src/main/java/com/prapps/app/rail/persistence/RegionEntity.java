package com.prapps.app.rail.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "region", schema = "trainapp")
public class RegionEntity {
	
	@Id
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "station_id")
	private StationEntity station;
	
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
	public StationEntity getStation() {
		return station;
	}
	public void setStation(StationEntity station) {
		this.station = station;
	}
}
