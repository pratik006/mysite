package com.prapps.app.trainapp.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "coach_composition", schema = "trainapp")
public class CoachCompositonEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CoachCompositionPk id;
	@Column(name = "coach_number")
	private String coachNumber;
	@Column(name = "coach_type")
	private String coachType;
	
	public CoachCompositionPk getId() {
		return id;
	}
	public void setId(CoachCompositionPk id) {
		this.id = id;
	}
	public String getCoachNumber() {
		return coachNumber;
	}
	public void setCoachNumber(String coachNumber) {
		this.coachNumber = coachNumber;
	}
	public String getCoachType() {
		return coachType;
	}
	public void setCoachType(String coachType) {
		this.coachType = coachType;
	}
}
