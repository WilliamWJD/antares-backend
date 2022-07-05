package com.antares.dto.endereco;

import java.io.Serializable;

import com.antares.domain.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String estado;
	private String cidade;
	private String bairro;
	private String cep;
	private String logradouro;
	private Integer numero;
	private Usuario usuario;
}
