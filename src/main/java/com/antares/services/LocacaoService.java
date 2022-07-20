package com.antares.services;

import com.antares.dto.locacao.LocacaoDto;

public interface LocacaoService {
	LocacaoDto salvarLocacao(LocacaoDto locacaoDto, Integer userId);
}
