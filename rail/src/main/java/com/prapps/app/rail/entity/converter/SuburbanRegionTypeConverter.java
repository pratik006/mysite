package com.prapps.app.rail.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.prapps.app.rail.type.SuburbanRegionType;

@Converter
public class SuburbanRegionTypeConverter implements AttributeConverter<SuburbanRegionType, String>{

	@Override
	public String convertToDatabaseColumn(SuburbanRegionType arg0) {
		return arg0.getCode();
	}

	@Override
	public SuburbanRegionType convertToEntityAttribute(String code) {
		return SuburbanRegionType.getByCode(code);
	}

}
