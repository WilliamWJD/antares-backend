package com.antares.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	private String nome;
	private Date dataNascimento;
	private String rg;
	private String cpf;
	private String profissao;
	private String estadoCivil;
	private String genero;
	private String email;
}
