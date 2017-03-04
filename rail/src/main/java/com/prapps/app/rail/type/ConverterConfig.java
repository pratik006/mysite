package com.prapps.app.rail.type;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ConverterConfig extends WebMvcConfigurationSupport {
	@Override
	public FormattingConversionService mvcConversionService() {
		FormattingConversionService f = super.mvcConversionService();
		f.addConverter(new SuburbanRegionTypeConverter());
		return f;
	}
}
