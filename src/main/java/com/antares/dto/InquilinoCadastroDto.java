package com.antares.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.antares.domain.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class InquilinoCadastroDto {
	@NotEmpty(message = "Preenchimento obrigatório")
	private String nome;
	private Date dataNascimento;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String rg;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpf;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String profissao;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String estadoCivil;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String genero;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "E-mail inválido")
	private String email;
	
	private Usuario usuario;
}
