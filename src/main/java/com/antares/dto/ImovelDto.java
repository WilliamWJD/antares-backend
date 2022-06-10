package com.antares.dto;

import java.io.Serializable;

import com.antares.domain.Usuario;

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
public class ImovelDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	private Boolean garagem;
	private double valor;
	private UsuarioDTO usuario;
}
