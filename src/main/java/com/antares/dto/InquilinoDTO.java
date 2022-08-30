package com.antares.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InquilinoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Date   dataNascimento;
	private String rg;
	private String cpf;
	private String profissao;
	private String estadoCivil;
	private String genero;
	private String email;
	private UsuarioResponseDTO usuario;
}
