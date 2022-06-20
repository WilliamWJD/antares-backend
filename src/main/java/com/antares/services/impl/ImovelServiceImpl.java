package com.antares.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antares.domain.Imovel;
import com.antares.domain.Usuario;
import com.antares.dto.imovel.ImovelDto;
import com.antares.dto.usuario.UsuarioDTO;
import com.antares.repository.ImovelRepository;
import com.antares.repository.UsuarioRepository;
import com.antares.services.ImovelService;
import com.antares.services.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImovelServiceImpl implements ImovelService {

	@Autowired
	private ImovelRepository imovelRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Optional<ImovelDto> save(ImovelDto imovelDto, Integer user_id) {
		Optional<Usuario> usuario = usuarioRepository.findById(user_id);

		if (!usuario.isPresent()) {
			throw new ObjectNotFoundException(
					"Usuário não encontrado com o id: " + user_id + ", tipo: " + Usuario.class.getName());
		}

		imovelDto.setUsuario(modelMapper.map(usuario.get(), UsuarioDTO.class));
		Imovel imovel = imovelRepository.save(modelMapper.map(imovelDto, Imovel.class));
		return Optional.of(modelMapper.map(imovel, ImovelDto.class));

	}

}
