package com.picoloto.projetoviasoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.picoloto.projetoviasoft.services.NfeService;

@SpringBootApplication
@EnableScheduling
public class BackendNfeApplication implements CommandLineRunner {

	@Autowired
	private NfeService service;

	public static void main(String[] args) {
		SpringApplication.run(BackendNfeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.verificaStatusDoServicoJob();
	}

}
