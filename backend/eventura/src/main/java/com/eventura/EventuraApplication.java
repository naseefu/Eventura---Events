package com.eventura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = "com.eventura")
@EntityScan(basePackages = "com.eventura")
@EnableJpaRepositories(basePackages = "com.eventura.repository")
public class EventuraApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventuraApplication.class, args);
	}
	
}