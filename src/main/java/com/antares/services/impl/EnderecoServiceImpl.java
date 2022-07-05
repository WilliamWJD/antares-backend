package com.antares.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antares.domain.Endereco;
import com.antares.dto.endereco.EnderecoDTO;
import com.antares.repository.EnderecoRepository;
import com.antares.services.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {
	private static final Logger logger = LoggerFactory.getLogger(EnderecoServiceImpl.class);
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<EnderecoDTO> salvar(List<EnderecoDTO> enderecoDTO) {
		logger.info("** Cadastro de Endereco - Salvando a listagem de endereços");
		
		List<Endereco> enderecos = enderecoRepository.saveAll(enderecoDTO.stream().map(item -> modelMapper.map(item, Endereco.class)).collect(Collectors.toList()));
		return enderecos.stream().map(item -> modelMapper.map(item, EnderecoDTO.class)).collect(Collectors.toList());
	}

}
