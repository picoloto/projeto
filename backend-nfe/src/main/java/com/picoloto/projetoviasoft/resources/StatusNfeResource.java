package com.picoloto.projetoviasoft.resources;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.picoloto.projetoviasoft.domain.StatusNfe;
import com.picoloto.projetoviasoft.resources.utils.URL;
import com.picoloto.projetoviasoft.services.JsoupService;
import com.picoloto.projetoviasoft.services.StatusNfeService;

@RestController
@RequestMapping(value = "/statusnfe")
public class StatusNfeResource {
	@Autowired
	private StatusNfeService statusNfeService;
	
	@Autowired
	private JsoupService jsoupService;

	@GetMapping(value = "/autorizadores")
	public ResponseEntity<List<StatusNfe>> findStatusAtualPorUf(
			@RequestParam(value = "uf", defaultValue = "") String uf) {
		String ufDecoded = URL.decodeParam(uf);
		List<StatusNfe> list = statusNfeService.verificaStatusAtualPorUf(ufDecoded);
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/listaAutorizadores")
	public ResponseEntity<List<String>> buscaAutorizadores() {
		List<String> ufsVersaoNova = jsoupService.verificaStatusDoServico().getUfsVersaoAntiga();
		return ResponseEntity.ok(ufsVersaoNova);
	}

	@GetMapping(value = "/maiorIndisponibilidade")
	public ResponseEntity<List<?>> buscaMaiorIndisponibilidade() {
		return ResponseEntity.ok(statusNfeService.findMaiorIndisponibilidade());
	}

	@GetMapping(value = "/dataHoraStatus")
	public ResponseEntity<List<?>> findByDataHoraStatus(
			@RequestParam(value = "data", defaultValue = "") String data) {
		String dataHoraDecoded = URL.decodeParam(data);
		
		LocalDateTime dataHoraInicialFormatada = LocalDateTime.parse(dataHoraDecoded + "T00:00:00");
		LocalDateTime dataHoraFinalFormatada = LocalDateTime.parse(dataHoraDecoded + "T23:59:59");

		return ResponseEntity.ok(statusNfeService.findByDataHoraStatus(dataHoraInicialFormatada, dataHoraFinalFormatada));
	}

	@GetMapping
	public ResponseEntity<List<StatusNfe>> findStatusAtual() {
		List<StatusNfe> list = statusNfeService.verificaVersaoAutorizadorNfeAtual();
		return ResponseEntity.ok(list);
	}

}
