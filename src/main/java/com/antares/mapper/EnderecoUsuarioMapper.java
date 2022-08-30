package com.antares.mapper;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.antares.domain.EnderecoUsuario;
import com.antares.domain.Usuario;
import com.antares.dto.EnderecoUsuarioDTO;

@Component
public class EnderecoUsuarioMapper implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public EnderecoUsuario mapearDtoParaEntity(EnderecoUsuarioDTO dto, Usuario usuario) {
		EnderecoUsuario entity = new EnderecoUsuario();
		
		entity.setId(dto.getId());
		entity.setEstado(dto.getEstado());
		entity.setCidade(dto.getCidade());
		entity.setBairro(dto.getBairro());
		entity.setCep(dto.getCep());
		entity.setLogradouro(dto.getLogradouro());
		entity.setNumero(dto.getNumero());
		entity.setUsuario(usuario);
		return entity;
	}
}
