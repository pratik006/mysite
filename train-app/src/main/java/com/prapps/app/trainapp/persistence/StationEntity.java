package com.prapps.app.trainapp.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "station", schema = "trainapp")
public class StationEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	
	@OneToMany
	@JoinColumn(name = "station_id", insertable = false, updatable = false)
	private List<TrainRouteEntity> trainRoutes;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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

	public List<TrainRouteEntity> getTrainRoutes() {
		return trainRoutes;
	}

	public void setTrainRoutes(List<TrainRouteEntity> trainRoutes) {
		this.trainRoutes = trainRoutes;
	}
}
