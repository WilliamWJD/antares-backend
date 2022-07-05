package com.antares.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String estado;
	private String cidade;
	private String bairro;
	private String cep;
	private String logradouro;
	private Integer numero;
	
	@ManyToOne(cascade =  CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
//	@OneToOne(mappedBy = "endereco")
//	private Imovel imovel;
}
