package com.picoloto.projetoviasoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendNfeApplication { //implements CommandLineRunner
	
	/*@Autowired
	private HistoricoNfeJobService service;*/ 
	
	public static void main(String[] args) {
		SpringApplication.run(BackendNfeApplication.class, args);
	}
	
	/*@Override
	public void run(String... args) throws Exception {
		service.verificaStatusDoServicoJob();
	}*/
}
