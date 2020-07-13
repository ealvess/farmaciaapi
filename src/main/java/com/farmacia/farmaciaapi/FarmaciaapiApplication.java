package com.farmacia.farmaciaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.farmacia.farmaciaapi.config.property.FarmaciaApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(FarmaciaApiProperty.class)
public class FarmaciaapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmaciaapiApplication.class, args);
	}

}
