package com.prapps.app.trainapp.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	@Column(name = "rundays")
	private String rundays;
	@Column(name = "pantry")
	private String pantry;
	@Column(name = "incl_food_cost")
	private String inclFoodCost;
	@Column(name = "total_travel_time")
	private Integer totalTravelTime;
	@Column(name = "avg_speed")
	private Integer avgSpeed;
	@Column(name = "adv_reservation_period")
	private Integer advanceReservationPeriod;
	@Column(name = "owner")
	private String owner;
	@Column(name = "rake_share")
	private String rakeShare;
	@Column(name = "fare_type")
	private String fareType;
	@Column(name = "gauge")
	private String gauge;
	@Column(name = "pair_train_id")
	private Long pairTrainId;
	
	@Column(name = "mon")
	private String mon;
	@Column(name = "tue")
	private String tue;
	@Column(name = "wed")
	private String wed;
	@Column(name = "thu")
	private String thu;
	@Column(name = "fri")
	private String fri;
	@Column(name = "sat")
	private String sat;
	@Column(name = "sun")
	private String sun;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "train_id")
	@Fetch(FetchMode.JOIN)
	private List<CoachCompositonEntity> coachCompositions;

	/*@ManyToOne(targetEntity = TrainEntity.class)
	@JoinColumn(name = "pair_train_id", insertable = false, updatable = false)*/
	private transient TrainEntity pairTrain;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "train_id", insertable = false, updatable = false)
	private List<TrainRouteEntity> routes;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
            name = "train_class", schema = "trainapp",
            joinColumns = @JoinColumn(name = "train_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
	@Fetch(FetchMode.JOIN)
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

	public String getRundays() {
		return rundays;
	}

	public void setRundays(String rundays) {
		this.rundays = rundays;
	}

	public String getPantry() {
		return pantry;
	}

	public void setPantry(String pantry) {
		this.pantry = pantry;
	}

	public String getInclFoodCost() {
		return inclFoodCost;
	}

	public void setInclFoodCost(String inclFoodCost) {
		this.inclFoodCost = inclFoodCost;
	}

	public Integer getTotalTravelTime() {
		return totalTravelTime;
	}

	public void setTotalTravelTime(Integer totalTravelTime) {
		this.totalTravelTime = totalTravelTime;
	}

	public Integer getAvgSpeed() {
		return avgSpeed;
	}

	public void setAvgSpeed(Integer avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public Integer getAdvanceReservationPeriod() {
		return advanceReservationPeriod;
	}

	public void setAdvanceReservationPeriod(Integer advanceReservationPeriod) {
		this.advanceReservationPeriod = advanceReservationPeriod;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getRakeShare() {
		return rakeShare;
	}

	public void setRakeShare(String rakeShare) {
		this.rakeShare = rakeShare;
	}

	public String getFareType() {
		return fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}

	public String getGauge() {
		return gauge;
	}

	public void setGauge(String gauge) {
		this.gauge = gauge;
	}

	public Long getPairTrainId() {
		return pairTrainId;
	}

	public void setPairTrainId(Long pairTrainId) {
		this.pairTrainId = pairTrainId;
	}

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}

	public String getTue() {
		return tue;
	}

	public void setTue(String tue) {
		this.tue = tue;
	}

	public String getWed() {
		return wed;
	}

	public void setWed(String wed) {
		this.wed = wed;
	}

	public String getThu() {
		return thu;
	}

	public void setThu(String thu) {
		this.thu = thu;
	}

	public String getFri() {
		return fri;
	}

	public void setFri(String fri) {
		this.fri = fri;
	}

	public String getSat() {
		return sat;
	}

	public void setSat(String sat) {
		this.sat = sat;
	}

	public String getSun() {
		return sun;
	}

	public void setSun(String sun) {
		this.sun = sun;
	}

	public List<CoachCompositonEntity> getCoachCompositions() {
		return coachCompositions;
	}

	public void setCoachCompositions(List<CoachCompositonEntity> coachCompositions) {
		this.coachCompositions = coachCompositions;
	}

	public TrainEntity getPairTrain() {
		return pairTrain;
	}

	public void setPairTrain(TrainEntity pairTrain) {
		this.pairTrain = pairTrain;
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
