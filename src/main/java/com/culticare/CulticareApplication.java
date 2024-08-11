package com.culticare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CulticareApplication {

	public static void main(String[] args) {
		SpringApplication.run(CulticareApplication.class, args);
	}

}
