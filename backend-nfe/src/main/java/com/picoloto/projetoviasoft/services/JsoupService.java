package com.picoloto.projetoviasoft.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.picoloto.projetoviasoft.domain.DadosRecuperados;

@Service
public class JsoupService {

	public DadosRecuperados verificaStatusDoServico() {
		List<String> versoes = new ArrayList<>();
		List<String> ufsVersaoAntiga = new ArrayList<>();
		List<String> imgsVersaoAntiga = new ArrayList<>();
		List<String> ufsVersaoNova = new ArrayList<>();
		List<String> imgsVersaoNova = new ArrayList<>();

		DadosRecuperados dadosRecuperados = new DadosRecuperados();

		try {
			Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();

			Elements captions = doc.getElementsByTag("caption").select(".fonte10");
			Elements trVersaoAntiga = doc.getElementById("ctl00_ContentPlaceHolder1_gdvDisponibilidade").select("tbody")
					.select("tr");
			Elements trVersaoNova = doc.getElementById("ctl00_ContentPlaceHolder1_gdvDisponibilidade2").select("tbody")
					.select("tr");

			for (Element cap : captions) {
				versoes.add(buscaVersoes(cap));
			}
			dadosRecuperados.setVersoes(versoes);

			for (Element tr : trVersaoAntiga) {
				if (buscaUfs(tr) != null) {
					ufsVersaoAntiga.add(buscaUfs(tr));
				}

				if (buscaStatus(tr) != null) {
					imgsVersaoAntiga.add(buscaStatus(tr));
				}
			}
			dadosRecuperados.setUfsVersaoAntiga(ufsVersaoAntiga);
			dadosRecuperados.setImgsVersaoAntiga(imgsVersaoAntiga);

			for (Element tr : trVersaoNova) {
				if (buscaUfs(tr) != null) {
					ufsVersaoNova.add(buscaUfs(tr));
				}

				if (buscaStatus(tr) != null) {
					imgsVersaoNova.add(buscaStatus(tr));
				}
			}
			dadosRecuperados.setUfsVersaoNova(ufsVersaoNova);
			dadosRecuperados.setImgsVersaoNova(imgsVersaoNova);

			return dadosRecuperados;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String buscaVersoes(Element cap) {
		String[] parts = cap.text().split("Vers");
		return parts[1].trim().replaceAll("[^0-9?!\\.]", "");
	}

	public String buscaUfs(Element tr) {
		if (tr.select("td").first() != null) {
			return tr.select("td").first().text();
		}
		return null;
	}

	public String buscaStatus(Element tr) {
		if (tr.select("td").size() > 0) {
			if (tr.select("td").get(5).select("img").attr("src").trim().contains("verde")) {
				return "ON";
			} else if (tr.select("td").get(5).select("img").attr("src").trim().contains("amarela")) {
				return "WAIT";
			} else if (tr.select("td").get(5).select("img").attr("src").trim().contains("vermelho")) {
				return "OFF";
			} else {
				return "NONE";
			}
		}
		return null;
	}
}
