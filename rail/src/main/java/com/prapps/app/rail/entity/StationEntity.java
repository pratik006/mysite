package com.prapps.app.rail.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prapps.app.rail.entity.converter.SuburbanRegionTypeConverter;
import com.prapps.app.rail.type.SuburbanRegionType;

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
	@Convert(converter = SuburbanRegionTypeConverter.class)
	private SuburbanRegionType type;
	@Column(name = "xcood")
	private Double xcoordinates;
	@Column(name = "ycood")
	private Double ycoordinates;
	
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
	public SuburbanRegionType getType() {
		return type;
	}
	public void setType(SuburbanRegionType type) {
		this.type = type;
	}
	public Double getXcoordinates() {
		return xcoordinates;
	}
	public void setXcoordinates(Double xcoordinates) {
		this.xcoordinates = xcoordinates;
	}
	public Double getYcoordinates() {
		return ycoordinates;
	}
	public void setYcoordinates(Double ycoordinates) {
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
