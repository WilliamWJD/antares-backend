package com.antares.mapper;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.antares.domain.Imovel;
import com.antares.domain.Inquilino;
import com.antares.domain.Locacao;
import com.antares.dto.LocacaoDtoEntrada;

@Component
public class LocacaoMapper implements Serializable {
	private static final long serialVersionUID = 1L;

	public Locacao mapearDtoParaEntity(LocacaoDtoEntrada locacaoDto, Inquilino inquilino, Imovel imovel) {
		Locacao entity = new Locacao();
		entity.setDataInicio(locacaoDto.getDataInicio());
		entity.setDataFim(locacaoDto.getDataFim());
		entity.setTempoContrato(locacaoDto.getTempoContrato());
		entity.setDiaPagamentoAluguel(locacaoDto.getDiaPagamentoAluguel());
		entity.setValorCaucao(locacaoDto.getValorCaucao());
		entity.setStatus(locacaoDto.getStatus());
		entity.setInquilino(inquilino);
		entity.setImovel(imovel);
		return entity;
	}
}
