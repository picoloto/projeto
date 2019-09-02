package com.picoloto.projetoviasoft.domain;

import java.io.Serializable;
import java.util.List;

public class DadosRecuperados implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<String> versoes;
	private List<String> ufsVersaoAntiga;
	private List<String> imgsVersaoAntiga;
	private List<String> ufsVersaoNova;
	private List<String> imgsVersaoNova;

	public DadosRecuperados() {
	}

	public List<String> getVersoes() {
		return versoes;
	}

	public void setVersoes(List<String> versoes) {
		this.versoes = versoes;
	}

	public List<String> getUfsVersaoAntiga() {
		return ufsVersaoAntiga;
	}

	public void setUfsVersaoAntiga(List<String> ufsVersaoAntiga) {
		this.ufsVersaoAntiga = ufsVersaoAntiga;
	}

	public List<String> getImgsVersaoAntiga() {
		return imgsVersaoAntiga;
	}

	public void setImgsVersaoAntiga(List<String> imgsVersaoAntiga) {
		this.imgsVersaoAntiga = imgsVersaoAntiga;
	}

	public List<String> getUfsVersaoNova() {
		return ufsVersaoNova;
	}

	public void setUfsVersaoNova(List<String> ufsVersaoNova) {
		this.ufsVersaoNova = ufsVersaoNova;
	}

	public List<String> getImgsVersaoNova() {
		return imgsVersaoNova;
	}

	public void setImgsVersaoNova(List<String> imgsVersaoNova) {
		this.imgsVersaoNova = imgsVersaoNova;
	}

	@Override
	public String toString() {
		return "DadosRecuperados [versoes=" + versoes + ", ufsVersaoAntiga=" + ufsVersaoAntiga + ", imgsVersaoAntiga="
				+ imgsVersaoAntiga + ", ufsVersaoNova=" + ufsVersaoNova + ", imgsVersaoNova=" + imgsVersaoNova + "]";
	}
	
	
	
}
