package com.prapps.app.trainapp.persistence;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CoachCompositionPk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "train_id")
	private Long trainId;
	@Column(name = "coach_seq_no")
	private Integer coachSeqNo;

	public CoachCompositionPk() {
		// TODO Auto-generated constructor stub
	}
	
	public CoachCompositionPk(Long trainId, Integer coachSeqNo) {
		this.trainId = trainId;
		this.coachSeqNo = coachSeqNo;
	}

	public Long getTrainId() {
		return trainId;
	}

	public void setTrainId(Long trainId) {
		this.trainId = trainId;
	}

	public Integer getCoachSeqNo() {
		return coachSeqNo;
	}

	public void setCoachSeqNo(Integer coachSeqNo) {
		this.coachSeqNo = coachSeqNo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(trainId, coachSeqNo);
	}
	
	@Override
	public boolean equals(Object otherObj) {
		if (otherObj instanceof CoachCompositionPk) {
			CoachCompositionPk other = (CoachCompositionPk) otherObj;
			return trainId == other.getTrainId() && coachSeqNo == other.getCoachSeqNo();
		}
		
		return false;
	}
	
}
