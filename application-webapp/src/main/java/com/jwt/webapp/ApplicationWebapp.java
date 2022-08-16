package com.jwt.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.jwt.repository" })
@EntityScan(basePackages = {"com.jwt.domain"})
@ComponentScan({"com.jwt"})
public class ApplicationWebapp {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationWebapp.class, args);
	}

}
