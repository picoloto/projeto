package com.picoloto.projetoviasoft.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StatusNfeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String autorizador;
	private String status;
	private String versao;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
	private Date dataHoraStatus;

	public StatusNfeDTO() {
	}

	public StatusNfeDTO(Integer id, String autorizador, String status, String versao, Date dataHoraStatus) {
		super();
		this.id = id;
		this.autorizador = autorizador;
		this.status = status;
		this.versao = versao;
		this.dataHoraStatus = dataHoraStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAutorizador() {
		return autorizador;
	}

	public void setAutorizador(String autorizador) {
		this.autorizador = autorizador;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public Date getDataHoraStatus() {
		return dataHoraStatus;
	}

	public void setDataHoraStatus(Date dataHoraStatus) {
		this.dataHoraStatus = dataHoraStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusNfeDTO other = (StatusNfeDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatusNfeDTO [id=" + id + ", autorizador=" + autorizador + ", status=" + status + ", versao=" + versao
				+ ", dataHoraStatus=" + dataHoraStatus + "]";
	}

}
