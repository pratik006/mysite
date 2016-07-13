package com.prapps.app.trainapp.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	@Column(name = "type")
	private String type;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "train_id", insertable = false, updatable = false)
	private List<TrainRouteEntity> routes;
	@OneToMany
	@JoinTable(
            name = "train_class", schema = "trainapp",
            joinColumns = @JoinColumn(name = "train_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
	private List<ClassEntity> classes;
	
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

	public List<TrainRouteEntity> getRoutes() {
		if (routes == null) {
			routes = new ArrayList<TrainRouteEntity>();
		}
		
		return routes;
	}

	public void setRoutes(List<TrainRouteEntity> routes) {
		this.routes = routes;
	}

	public List<ClassEntity> getClasses() {
		if (classes == null) {
			classes = new ArrayList<ClassEntity>();
		}
		
		return classes;
	}

	public void setClasses(List<ClassEntity> classes) {
		this.classes = classes;
	}
}
