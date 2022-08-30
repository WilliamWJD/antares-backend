package com.antares.mapper;

import java.io.Serializable;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.antares.domain.Usuario;
import com.antares.domain.enums.Perfil;
import com.antares.dto.UsuarioDTO;

@Component
public class UsuarioMapper implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EnderecoUsuarioMapper enderecoUsuarioMapper;
	
	public Usuario mapearDtoParaEntity(UsuarioDTO dto) {
		Usuario entity = new Usuario();
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setDataNascimento(dto.getDataNascimento());
		entity.setRg(dto.getRg());
		entity.setCpf(dto.getCpf());
		entity.setProfissao(dto.getProfissao());
		entity.setEstadoCivil(dto.getEstadoCivil());
		entity.setGenero(dto.getGenero());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setEnderecos(dto.getEnderecos().stream().map(endereco -> enderecoUsuarioMapper.mapearDtoParaEntity(endereco, entity)).collect(Collectors.toList()));
		if(dto.getPerfil() != null) {
			entity.addPerfil(Perfil.toEnum(dto.getPerfil()));			
		}
		return entity;
	}
}
