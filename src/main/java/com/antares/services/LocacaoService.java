package com.antares.services;

import com.antares.dto.LocacaoDto;
import com.antares.dto.LocacaoDtoEntrada;

public interface LocacaoService {
	LocacaoDto salvarLocacao(LocacaoDtoEntrada locacaoDtoEntrada);
	LocacaoDto realizaRenovacaoContrato(LocacaoDto locacaoDto);
}
