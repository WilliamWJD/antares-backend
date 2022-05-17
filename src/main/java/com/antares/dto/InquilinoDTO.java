package com.antares.dto;

import java.util.Date;

import com.antares.domain.Inquilino;
import com.antares.domain.Usuario;

public class InquilinoDTO {
	private Integer id;
	private String nome;
	private Date dataNascimento;
	private String rg;
	private String cpf;
	private String profissao;
	private String estadoCivil;
	private String genero;
	private String email;
	private Usuario usuario;
	
	public InquilinoDTO(Inquilino obj) {
		this.setId(obj.getId());
		this.nome = obj.getNome();
		this.dataNascimento = obj.getDataNascimento();
		this.rg = obj.getRg();
		this.cpf = obj.getCpf();
		this.profissao = obj.getProfissao();
		this.estadoCivil = obj.getEstadoCivil();
		this.genero = obj.getGenero();
		this.email = obj.getEmail();
		this.usuario = obj.getUsuario();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "InquilinoDTO [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", rg=" + rg
				+ ", cpf=" + cpf + ", profissao=" + profissao + ", estadoCivil=" + estadoCivil + ", genero=" + genero
				+ ", email=" + email + ", usuario=" + usuario + "]";
	}
}
