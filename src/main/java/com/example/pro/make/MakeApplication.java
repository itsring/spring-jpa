package com.example.pro.make;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakeApplication.class, args);
	}

}
