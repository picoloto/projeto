package com.picoloto.projetoviasoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendNfeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendNfeApplication.class, args);
	}
}
