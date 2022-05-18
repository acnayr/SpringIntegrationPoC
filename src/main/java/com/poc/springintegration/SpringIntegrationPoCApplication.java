package com.poc.springintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class SpringIntegrationPoCApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationPoCApplication.class, args);
	}



	@Bean
	public RulesConfiguration rulesConfiguration(){
		RulesConfiguration configuration = null;
		try {
			configuration = new RulesConfiguration();
			configuration.loadConfiguration();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return configuration;
	}

}
