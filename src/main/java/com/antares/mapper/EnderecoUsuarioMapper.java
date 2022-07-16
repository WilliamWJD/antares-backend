package com.antares.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.antares.domain.EnderecoUsuario;
import com.antares.dto.endereco.EnderecoUsuarioDTO;

@Component
public class EnderecoUsuarioMapper {
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	public EnderecoUsuario mapearDtoParaEntity(EnderecoUsuarioDTO dto) {
		EnderecoUsuario entity = new EnderecoUsuario();
		entity.setId(dto.getId());
		entity.setEstado(dto.getEstado());
		entity.setCidade(dto.getCidade());
		entity.setBairro(dto.getBairro());
		entity.setCep(dto.getCep());
		entity.setLogradouro(dto.getLogradouro());
		entity.setNumero(dto.getNumero());
//		entity.setUsuario(usuarioMapper.mapearDtoParaEntity(dto.getUsuario()));
		return entity;
	}
}
