package com.prapps.app.trainapp.dto;

import java.io.Serializable;
import java.util.List;

public class Train implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private List<TrainStation> routes;
	
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

	public List<TrainStation> getRoutes() {
		return routes;
	}

	public void setRoutes(List<TrainStation> routes) {
		this.routes = routes;
	}
}
