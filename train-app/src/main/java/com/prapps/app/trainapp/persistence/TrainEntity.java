package com.prapps.app.trainapp.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "train", schema = "trainapp")
public class TrainEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@OneToMany
	@JoinColumn(name = "train_id", insertable = false, updatable = false)
	private List<TrainRouteEntity> routes;
	
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

	public List<TrainRouteEntity> getRoutes() {
		return routes;
	}

	public void setRoutes(List<TrainRouteEntity> routes) {
		this.routes = routes;
	}
}
