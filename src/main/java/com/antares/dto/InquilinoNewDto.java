package com.antares.dto;

import java.util.Date;

public class InquilinoNewDto {
	private String nome;
	private Date dataNascimento;
	private String rg;
	private String cpf;
	private String profissao;
	private String estadoCivil;
	private String genero;
	private String email;
	private Integer usuario_id;
	
	public InquilinoNewDto() {
	}

	public InquilinoNewDto(String nome, Date dataNascimento, String rg, String cpf, String profissao,
			String estadoCivil, String genero, String email, Integer usuario_id) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.rg = rg;
		this.cpf = cpf;
		this.profissao = profissao;
		this.estadoCivil = estadoCivil;
		this.genero = genero;
		this.email = email;
		this.setUsuario_id(usuario_id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Integer usuario_id) {
		this.usuario_id = usuario_id;
	}

	@Override
	public String toString() {
		return "InquilinoNewDto [nome=" + nome + ", dataNascimento=" + dataNascimento + ", rg=" + rg + ", cpf=" + cpf
				+ ", profissao=" + profissao + ", estadoCivil=" + estadoCivil + ", genero=" + genero + ", email="
				+ email + ", usuario_id=" + usuario_id + "]";
	}	
}