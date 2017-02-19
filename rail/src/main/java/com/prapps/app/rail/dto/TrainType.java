package com.prapps.app.rail.dto;

public enum TrainType {
	MMTS("MMTS"),
	RAJDHANI("Rajdhani"),
	INTERCITY("Intercity Express");
	
	private String type;

	TrainType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public static TrainType getByType(String type) {
		for (TrainType trainType : TrainType.values()) {
			if (type.equalsIgnoreCase(trainType.getType())) {
				return trainType;
			}
		}
		
		return null;
	}
}
