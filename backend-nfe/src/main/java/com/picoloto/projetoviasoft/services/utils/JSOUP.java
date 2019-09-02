package com.picoloto.projetoviasoft.services.utils;

import org.jsoup.nodes.Element;

public class JSOUP {
	public static String buscaVersoes(Element cap) {
		String[] parts = cap.text().split("Vers");
		return parts[1].trim().replaceAll("[^0-9?!\\.]", "");
	}

	public static String buscaUfs(Element tr) {
		if (tr.select("td").first() != null) {
			return tr.select("td").first().text();
		}
		return null;
	}

	public static String buscaStatus(Element tr) {
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
