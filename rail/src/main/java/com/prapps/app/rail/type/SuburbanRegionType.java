package com.prapps.app.rail.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SuburbanRegionType {
	Hyderabad("hyd-mmts"),
	ER("er-suburban"),
	ALL("");
	
	private String code;

	SuburbanRegionType(String code) {
		this.code = code;
	}
	
	@JsonValue
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public static SuburbanRegionType getByCode(String code) {
		for (SuburbanRegionType type : SuburbanRegionType.values()) {
			if (type.getCode().equalsIgnoreCase(code)) {
				return type;
			}
		}
		
		return ALL;
	}
	
}
