package com.group1.sppam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SppamApplication {

	public static void main(String[] args) {
		SpringApplication.run(SppamApplication.class, args);
	}

}
