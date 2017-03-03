package com.prapps.app.rail.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@Convert( converter=TrainTypeConverter.class )
	private TrainType type;
	@Column(name = "rundays")
	private String rundays;
	
	@JoinColumn(name = "train_id")
	@OneToMany(cascade = CascadeType.PERSIST)
	private Set<RouteEntity> routes;
	
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
	public Set<RouteEntity> getRoutes() {
		if (routes ==  null) {
			routes = new LinkedHashSet<RouteEntity>();
		}
		
		return routes;
	}
	public void setRoutes(Set<RouteEntity> routes) {
		this.routes = routes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrainEntity other = (TrainEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
