package com.antares.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antares.domain.EnderecoUsuario;
import com.antares.domain.Usuario;
import com.antares.dto.endereco.EnderecoUsuarioDTO;
import com.antares.repository.EnderecoUsuarioRepository;
import com.antares.services.EnderecoUsuarioService;

@Service
public class EnderecoUsuarioServiceImpl implements EnderecoUsuarioService{
	
	@Autowired
	private EnderecoUsuarioRepository enderecoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<EnderecoUsuarioDTO> salvar(List<EnderecoUsuarioDTO> enderecos, Usuario usuario) {
		enderecos.stream().forEach(item -> item.setUsuario(usuario));
		List<EnderecoUsuario> ederecosList = enderecoRepository.saveAll(enderecos.stream().map(item -> modelMapper.map(item, EnderecoUsuario.class)).collect(Collectors.toList()));
		return ederecosList.stream().map(item -> modelMapper.map(item, EnderecoUsuarioDTO.class)).collect(Collectors.toList());
	}

}
