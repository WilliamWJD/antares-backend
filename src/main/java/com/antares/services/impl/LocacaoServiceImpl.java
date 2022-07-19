package com.antares.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antares.dto.locacao.LocacaoDto;
import com.antares.repository.LocacaoRepository;
import com.antares.services.LocacaoService;

@Service
public class LocacaoServiceImpl implements LocacaoService{
	
	@Autowired
	private LocacaoRepository locacaoRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public LocacaoDto salvarLocacao(LocacaoDto locacaoDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
