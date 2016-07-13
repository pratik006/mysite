package com.prapps.app.trainapp.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "class", schema = "trainapp")
public class ClassEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;
	@Column(name = "type", insertable = false, updatable = false)
	private String type;
	
	public ClassEntity() {
		
	}
	
	public ClassEntity(Long id, String type) {
		this.id = id;
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
