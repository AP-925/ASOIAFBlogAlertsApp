package com.notifierapp.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.notifierapp.*")
@EnableJpaRepositories(basePackages = "com.notifierapp.Repository")
@EntityScan(basePackages = "com.notifierapp.Entity")
public class NotifierAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifierAppApplication.class, args);
	}

}
