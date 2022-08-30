package com.antares.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.antares.domain.Imovel;
import com.antares.dto.ImovelDto;

@Component
public class ImovelMapper {
	
	@Autowired
	private EnderecoMapper enderecoMapper;
	
	@Autowired
	private UsuarioMapper usuarioMappper;
	
	public Imovel mapearDtoParaEntity(ImovelDto dto) {
		Imovel entity = new Imovel();
		entity.setId(dto.getId());
		entity.setDescricao(dto.getDescricao());
		entity.setValor(dto.getValor());
		entity.setEnderecoImovel(enderecoMapper.mapearDtoParaEntity(dto.getEnderecoImovel()));
		entity.setUsuario(usuarioMappper.mapearDtoParaEntity(dto.getUsuario()));
		return entity;
	}
}
