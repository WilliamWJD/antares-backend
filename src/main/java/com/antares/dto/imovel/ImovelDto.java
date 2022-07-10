package com.antares.dto.imovel;

import java.io.Serializable;

import com.antares.dto.endereco.EnderecoImovelDTO;
import com.antares.dto.usuario.UsuarioDTO;

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
	
	private Integer id;
	private String descricao;
	private Boolean garagem;
	private double valor;
	private UsuarioDTO usuario;
	private EnderecoImovelDTO enderecoImovel;
}
