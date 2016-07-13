package com.prapps.app.trainapp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Train implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String type;
	private List<TrainStation> routes;
	private List<String> classes;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<TrainStation> getRoutes() {
		if (routes == null) {
			routes = new ArrayList<TrainStation>();
		}
		
		return routes;
	}

	public void setRoutes(List<TrainStation> routes) {
		this.routes = routes;
	}

	public List<String> getClasses() {
		if (classes == null) {
			classes = new ArrayList<String>();
		}
		
		return classes;
	}

	public void setClasses(List<String> reservationClasses) {
		this.classes = reservationClasses;
	}
}
