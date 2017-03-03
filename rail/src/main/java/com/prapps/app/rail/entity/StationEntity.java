package com.prapps.app.rail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "station", schema = "trainapp")
public class StationEntity {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	@Column(name = "type")
	private String type;
	@Column(name = "xcood")
	private Float xcoordinates;
	@Column(name = "ycood")
	private Float ycoordinates;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Float getXcoordinates() {
		return xcoordinates;
	}
	public void setXcoordinates(Float xcoordinates) {
		this.xcoordinates = xcoordinates;
	}
	public Float getYcoordinates() {
		return ycoordinates;
	}
	public void setYcoordinates(Float ycoordinates) {
		this.ycoordinates = ycoordinates;
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
		StationEntity other = (StationEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
