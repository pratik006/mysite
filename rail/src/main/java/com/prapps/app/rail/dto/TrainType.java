package com.prapps.app.rail.dto;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TrainType {
	Special("Special"),
	Suvidha("Suvidha"),
	Mail("Mail & Express"),
	Shatabdi("Shatabdi"),
	JanShatabdi("Jan Shatabdi"),
	SUPER_FAST("Super Fast"),
	Duranto("Duranto"),
	Rajdhani("Rajdhani"),
	Sampark_Kranti("Sampark Kranti"),
	INTERCITY("Intercity Express"),
	Garib_Rath("Garib Rath"),
	Slip("Slip"),
	Link("Link"),
	EMU("EMU"),
	MEMU("MEMU"),
	MMTS("MMTS"),
	Passenger("Passenger"),
	DMU("DMU");

	private static EnumSet<TrainType> SUBURBAN = EnumSet.of(MMTS, EMU, DMU, Passenger);
	
	private String type;

	TrainType(String type) {
		this.type = type;
	}
	
	@JsonValue
	public String getType() {
		return type;
	}
	
	@JsonCreator
	public TrainType setType(String type) {
		return getByType(type);
	}
	
	public static TrainType getByType(String type) {
		for (TrainType trainType : TrainType.values()) {
			if (type.equalsIgnoreCase(trainType.getType())) {
				return trainType;
			}
		}
		
		return null;
	}
	
	public static List<TrainType> getSuburbanTrainTypes() {
		List<TrainType> trainTypes = new ArrayList<TrainType>(SUBURBAN.size());
		for (TrainType type : SUBURBAN) {
			trainTypes.add(type);
		}
		return trainTypes;
	}
}
