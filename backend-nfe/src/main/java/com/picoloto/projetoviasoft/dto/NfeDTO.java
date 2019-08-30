package com.picoloto.projetoviasoft.dto;

import java.io.Serializable;

import com.picoloto.projetoviasoft.domain.Nfe;

public class NfeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;

	private String name;
	private String email;

	public NfeDTO() {
	}

	public NfeDTO(Nfe obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
