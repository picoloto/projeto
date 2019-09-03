package com.picoloto.projetoviasoft.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.picoloto.projetoviasoft.domain.StatusNfe;
import com.picoloto.projetoviasoft.exceptions.ParseCustomException;
import com.picoloto.projetoviasoft.resources.utils.URL;
import com.picoloto.projetoviasoft.services.JsoupService;
import com.picoloto.projetoviasoft.services.StatusNfeService;

@RestController
@RequestMapping(value = "/statusnfe")
public class StatusNfeResource {
	@Autowired
	private StatusNfeService service;

	@GetMapping(value = "/autorizadores")
	public ResponseEntity<List<StatusNfe>> findStatusAtualPorUf(
			@RequestParam(value = "uf", defaultValue = "") String uf) {
		String ufDecoded = URL.decodeParam(uf);
		List<StatusNfe> list = service.verificaStatusAtualPorUf(ufDecoded);
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/listaAutorizadores")
	public ResponseEntity<List<String>> buscaAutorizadores() {
		List<String> ufsVersaoNova = JsoupService.verificaStatusDoServico().getUfsVersaoAntiga();
		return ResponseEntity.ok(ufsVersaoNova);
	}

	@GetMapping(value = "/maiorIndisponibilidade")
	public ResponseEntity<List<?>> buscaMaiorIndisponibilidade() {
		return ResponseEntity.ok(service.findMaiorIndisponibilidade());
	}

	@GetMapping(value = "/dataHoraStatus")
	public ResponseEntity<List<?>> findByDataHoraStatus(
			@RequestParam(value = "dataHora", defaultValue = "") String dataHora) {
		String dataHoraDecoded = URL.decodeParam(dataHora);
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dataHoraFormatada = new Date();
		try {
			dataHoraFormatada = sft.parse(dataHoraDecoded);
		} catch (ParseException e) {
			throw new ParseCustomException("Data inválida.\nFormato aceito: yyyy-MM-dd HH:mm:ss");
		}

		return ResponseEntity.ok(service.findByDataHoraStatus(dataHoraFormatada));
	}

	@GetMapping
	public ResponseEntity<List<StatusNfe>> findStatusAtual() {
		List<StatusNfe> list = StatusNfeService.verificaVersaoAutorizadorNfeAtual();
		return ResponseEntity.ok(list);
	}

}
