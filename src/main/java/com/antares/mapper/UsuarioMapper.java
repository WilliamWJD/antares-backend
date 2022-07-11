package com.antares.mapper;

import org.springframework.stereotype.Component;

import com.antares.domain.Usuario;
import com.antares.dto.usuario.UsuarioDTO;

@Component
public class UsuarioMapper {
	
	public Usuario mapearDtoParaEntity(UsuarioDTO dto) {
		Usuario entity = new Usuario();
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setRg(dto.getRg());
		entity.setCpf(dto.getCpf());
		entity.setProfissao(dto.getProfissao());
		entity.setEstadoCivil(dto.getEstadoCivil());
		entity.setGenero(dto.getGenero());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		return entity;
	}
}
