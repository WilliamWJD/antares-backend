package com.antares.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antares.domain.EnderecoImovel;
import com.antares.dto.endereco.EnderecoImovelDTO;
import com.antares.repository.EnderecoImovelRepository;
import com.antares.services.EnderecoImovelService;

@Service
public class EnderecoImovelServiceImpl implements EnderecoImovelService{
	
	@Autowired
	private EnderecoImovelRepository enderecoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public EnderecoImovelDTO salvar(EnderecoImovelDTO endereco) {
		EnderecoImovel enderecoImovel = enderecoRepository.save(modelMapper.map(endereco, EnderecoImovel.class));
		return modelMapper.map(enderecoImovel, EnderecoImovelDTO.class);
	}

}
