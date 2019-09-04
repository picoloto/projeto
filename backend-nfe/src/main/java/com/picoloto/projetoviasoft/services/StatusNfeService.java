package com.picoloto.projetoviasoft.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picoloto.projetoviasoft.domain.DadosRecuperados;
import com.picoloto.projetoviasoft.domain.StatusNfe;
import com.picoloto.projetoviasoft.repository.StatusNfeRepository;

@Service
public class StatusNfeService {

	@Autowired
	private StatusNfeRepository statusNfeRepository;

	@Autowired
	private JsoupService jsoupService;

	public List<StatusNfe> verificaStatusAtualPorUf(String uf) {
		List<StatusNfe> versoesNfe = verificaVersaoAutorizadorNfeAtual();

		List<StatusNfe> versoesFiltradas = new ArrayList<>();

		for (StatusNfe versaoNfe : versoesNfe) {
			if (versaoNfe.getAutorizador().equals(uf.toUpperCase())) {
				versoesFiltradas.add(versaoNfe);
			}
		}

		return versoesFiltradas;
	}

	public List<StatusNfe> verificaVersaoAutorizadorNfeAtual() {
		DadosRecuperados dadosRecuperados = jsoupService.verificaStatusDoServico();
		List<String> versoes = dadosRecuperados.getVersoes();
		List<String> ufsVersaoAntiga = dadosRecuperados.getUfsVersaoAntiga();
		List<String> imgsVersaoAntiga = dadosRecuperados.getImgsVersaoAntiga();
		List<String> ufsVersaoNova = dadosRecuperados.getUfsVersaoNova();
		List<String> imgsVersaoNova = dadosRecuperados.getImgsVersaoNova();

		List<StatusNfe> statusNfes = new ArrayList<>();

		for (int i = 0; i < ufsVersaoAntiga.size(); i++) {
			StatusNfe statusNfe = new StatusNfe(null, versoes.get(0), ufsVersaoAntiga.get(i), imgsVersaoAntiga.get(i),
					LocalDateTime.now());
			statusNfes.add(statusNfe);
		}

		for (int i = 0; i < ufsVersaoNova.size(); i++) {
			StatusNfe statusNfe = new StatusNfe(null, versoes.get(1), ufsVersaoNova.get(i), imgsVersaoNova.get(i),
					LocalDateTime.now());
			statusNfes.add(statusNfe);
		}

		return statusNfes;
	}

	public List<?> findMaiorIndisponibilidade() {
		return statusNfeRepository.findMaiorIndisponibilidade();
	}

	public List<?> findByDataHoraStatus(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal) {
		return statusNfeRepository.findPorData(dataHoraInicial, dataHoraFinal);
	}
}
