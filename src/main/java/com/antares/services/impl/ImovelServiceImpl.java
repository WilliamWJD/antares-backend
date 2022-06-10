package com.antares.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antares.domain.Imovel;
import com.antares.domain.Usuario;
import com.antares.dto.ImovelDto;
import com.antares.dto.UsuarioDTO;
import com.antares.repository.ImovelRepository;
import com.antares.repository.UsuarioRepository;
import com.antares.services.ImovelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImovelServiceImpl implements ImovelService{
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Optional<ImovelDto> save(ImovelDto imovelDto, Integer user_id) {
		Optional<Usuario> usuario = usuarioRepository.findById(user_id);
		
		if (usuario.isPresent()) {
			imovelDto.setUsuario(modelMapper.map(usuario.get(), UsuarioDTO.class));
			Imovel imovel = imovelRepository.save(modelMapper.map(imovelDto, Imovel.class));
			return Optional.of(modelMapper.map(imovel, ImovelDto.class));
		}
		return Optional.of(null);
	}

}
