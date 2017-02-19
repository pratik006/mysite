package com.prapps.app.rail.dto;

public enum SearchType {
	DEFAULT("default"),
	HYD_MMTS("hyd-mmts");

	private String type;
	
	SearchType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public SearchType getByType(String type) {
		for (SearchType value : SearchType.values()) {
			if (type.equals(value.getType())) {
				return value;
			}
		}
		
		return DEFAULT;
	}
}
