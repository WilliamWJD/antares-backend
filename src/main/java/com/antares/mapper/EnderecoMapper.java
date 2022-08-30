package com.antares.mapper;

import org.springframework.stereotype.Component;

import com.antares.domain.EnderecoImovel;
import com.antares.dto.EnderecoImovelDTO;

@Component
public class EnderecoMapper {
	public EnderecoImovel mapearDtoParaEntity(EnderecoImovelDTO dto) {
		EnderecoImovel entity = new EnderecoImovel();
		entity.setId(dto.getId());
		entity.setEstado(dto.getEstado());
		entity.setCidade(dto.getCidade());
		entity.setBairro(dto.getBairro());
		entity.setCep(dto.getCep());
		entity.setLogradouro(dto.getLogradouro());
		entity.setNumero(dto.getNumero());
		return entity;
	}
}
