package com.antares.mapper;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.antares.domain.Imovel;
import com.antares.domain.Inquilino;
import com.antares.domain.Locacao;
import com.antares.domain.Usuario;
import com.antares.dto.locacao.LocacaoDto;

@Component
public class LocacaoMapper implements Serializable {
	private static final long serialVersionUID = 1L;

	public Locacao mapearDtoParaEntity(LocacaoDto locacaoDto, Inquilino inquilino, Imovel imovel) {
		Locacao entity = new Locacao();
		entity.setDataInicio(locacaoDto.getDataInicio());
		entity.setDataFim(locacaoDto.getDataFim());
		entity.setStatus(locacaoDto.getStatus());
		entity.setInquilino(inquilino);
		entity.setImovel(imovel);
		return entity;
	}
}
