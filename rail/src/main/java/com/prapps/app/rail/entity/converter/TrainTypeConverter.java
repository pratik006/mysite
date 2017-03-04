package com.prapps.app.rail.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.prapps.app.rail.dto.TrainType;

@Converter
public class TrainTypeConverter implements AttributeConverter<TrainType, String> {

	@Override
	public String convertToDatabaseColumn(TrainType type) {
		return type.getType();
	}

	@Override
	public TrainType convertToEntityAttribute(String type) {
		return TrainType.getByType(type);
	}

}