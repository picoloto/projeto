package com.picoloto.projetoviasoft.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.picoloto.projetoviasoft.domain.StatusNfe;
import com.picoloto.projetoviasoft.repository.StatusNfeRepository;

@Service
@EnableScheduling
public class HistoricoNfeJobService {

	@Autowired
	private StatusNfeRepository statusNfeRepository;

	@Scheduled(cron = "0 0/5 * 1/1 * ?")
	public void armazenaStatusDoServicoJob() {
		List<StatusNfe> nfes = StatusNfeService.verificaVersaoAutorizadorNfeAtual();

		statusNfeRepository.saveAll(nfes);
	}
}
