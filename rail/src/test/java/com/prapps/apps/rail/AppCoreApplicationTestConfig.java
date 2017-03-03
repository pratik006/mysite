package com.prapps.apps.rail;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.prapps.app.rail.repo")
@EntityScan(basePackages = "com.prapps.app.rail.entity")
public class AppCoreApplicationTestConfig extends SpringBootServletInitializer {

    public static void main(String[] args) {
    	TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        SpringApplication.run(AppCoreApplicationTestConfig.class, args);
    }
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppCoreApplicationTestConfig.class);
    }
	
}
