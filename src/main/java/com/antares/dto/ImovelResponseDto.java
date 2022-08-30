package com.antares.dto;

import java.io.Serializable;

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
public class ImovelResponseDto implements Serializable{
private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private Boolean garagem;
	private double valor;
	private UsuarioResponseDTO usuario;
	private EnderecoImovelDTO enderecoImovel;
}
