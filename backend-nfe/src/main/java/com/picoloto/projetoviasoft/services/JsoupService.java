package com.picoloto.projetoviasoft.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.picoloto.projetoviasoft.domain.DadosRecuperados;
import com.picoloto.projetoviasoft.services.utils.JSOUP;

public class JsoupService {

	public static DadosRecuperados verificaStatusDoServico() {
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
				versoes.add(JSOUP.buscaVersoes(cap));
			}
			dadosRecuperados.setVersoes(versoes);

			for (Element tr : trVersaoAntiga) {
				if (JSOUP.buscaUfs(tr) != null) {
					ufsVersaoAntiga.add(JSOUP.buscaUfs(tr));
				}

				if (JSOUP.buscaStatus(tr) != null) {
					imgsVersaoAntiga.add(JSOUP.buscaStatus(tr));
				}
			}
			dadosRecuperados.setUfsVersaoAntiga(ufsVersaoAntiga);
			dadosRecuperados.setImgsVersaoAntiga(imgsVersaoAntiga);

			for (Element tr : trVersaoNova) {
				if (JSOUP.buscaUfs(tr) != null) {
					ufsVersaoNova.add(JSOUP.buscaUfs(tr));
				}

				if (JSOUP.buscaStatus(tr) != null) {
					imgsVersaoNova.add(JSOUP.buscaStatus(tr));
				}
			}
			dadosRecuperados.setUfsVersaoNova(ufsVersaoNova);
			dadosRecuperados.setImgsVersaoNova(imgsVersaoNova);

			System.out.println(dadosRecuperados.toString());

			return dadosRecuperados;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
