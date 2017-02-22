package com.prapps.app.rail.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.prapps.app.rail.dto.TrainType;

@Entity
@Table(name = "train", schema =  "trainapp")
public class TrainEntity {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private TrainType type;
	@Column(name = "rundays")
	private String rundays;
	
	@JoinColumn(name = "train_id")
	@OneToMany
	@Fetch(FetchMode.JOIN)
	private List<RouteEntity> routes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TrainType getType() {
		return type;
	}
	public void setType(TrainType type) {
		this.type = type;
	}
	public String getRundays() {
		return rundays;
	}
	public void setRundays(String runDays) {
		this.rundays = runDays;
	}
	public List<RouteEntity> getRoutes() {
		return routes;
	}
	public void setRoutes(List<RouteEntity> routes) {
		this.routes = routes;
	}
}
