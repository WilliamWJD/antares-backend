package com.antares.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.antares.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Date dataNascimento;
	@Column(unique = true)
	private String rg;
	@Column(unique = true)
	private String cpf;
	private String profissao;
	private String estadoCivil;
	private String genero;
	@Column(unique = true)
	private String email;
	private String password;

	public Usuario() {
		addPerfil(Perfil.USER);
	}
	
	public Usuario(Integer id, String nome, Date dataNascimento, String rg, String cpf, String profissao,
			String estadoCivil, String genero, String email, String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.rg = rg;
		this.cpf = cpf;
		this.profissao = profissao;
		this.estadoCivil = estadoCivil;
		this.genero = genero;
		this.email = email;
		this.password = password;
		addPerfil(Perfil.USER);
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Inquilino> inquilinos = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Imovel> imoveis = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Locacao> locacao = new ArrayList<>();
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<EnderecoUsuario> enderecos;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	public Set<Perfil> getPerfis(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
}
