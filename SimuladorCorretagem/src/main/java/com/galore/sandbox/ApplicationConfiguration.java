package com.galore.sandbox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
	@Bean
	public CalculadoraFactory calculadoraFactory() {
		return new CalculadoraFactoryImpl();
	}
}
