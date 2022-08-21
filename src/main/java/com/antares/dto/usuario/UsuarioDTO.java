package com.antares.dto.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.antares.dto.endereco.EnderecoUsuarioDTO;

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
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String nome;

	private Date dataNascimento;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String rg;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpf;
	
	private String profissao;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String estadoCivil;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String genero;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "E-mail inválido")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Size(min = 6, max = 12)
	private String password;
	
	private List<EnderecoUsuarioDTO> enderecos = new ArrayList<>();
	
	private Integer perfil;
}
