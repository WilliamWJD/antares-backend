package com.antares.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antares.domain.Endereco;
import com.antares.domain.Usuario;
import com.antares.dto.endereco.EnderecoDTO;
import com.antares.repository.EnderecoRepository;
import com.antares.services.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService{
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<EnderecoDTO> salvar(List<EnderecoDTO> enderecos, Usuario usuario) {
		enderecos.stream().forEach(item -> item.setUsuario(usuario));
		List<Endereco> ederecosList = enderecoRepository.saveAll(enderecos.stream().map(item -> modelMapper.map(item, Endereco.class)).collect(Collectors.toList()));
		return ederecosList.stream().map(item -> modelMapper.map(item, EnderecoDTO.class)).collect(Collectors.toList());
	}

}
