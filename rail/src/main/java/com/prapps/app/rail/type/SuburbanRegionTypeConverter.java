package com.prapps.app.rail.type;

import org.springframework.core.convert.converter.Converter;

public class SuburbanRegionTypeConverter implements Converter<String, SuburbanRegionType> {

	@Override
	public SuburbanRegionType convert(String code) {
		return SuburbanRegionType.getByCode(code);
	}

}
