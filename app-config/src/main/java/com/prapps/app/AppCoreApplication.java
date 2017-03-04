package com.prapps.app;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
public class AppCoreApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
    	TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        SpringApplication.run(AppCoreApplication.class, args);
    }
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppCoreApplication.class);
    }

}
