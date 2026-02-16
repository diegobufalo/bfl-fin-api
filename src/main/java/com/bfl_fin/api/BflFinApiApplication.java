package com.bfl_fin.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BflFinApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BflFinApiApplication.class, args);
	}

}
