package com.antares.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.antares.domain.Inquilino;
import com.antares.domain.Usuario;
import com.antares.dto.InquilinoCadastroDto;
import com.antares.dto.InquilinoDTO;
import com.antares.repository.InquilinoRepository;
import com.antares.repository.UsuarioRepository;
import com.antares.services.InquilinoService;
import com.antares.services.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InquilinoServiceImpl implements InquilinoService {

	private final InquilinoRepository inquilinoRepository;
	private final UsuarioRepository usuarioRepository;
	private final ModelMapper modelMapper;

	@Override
	public InquilinoDTO save(InquilinoCadastroDto inquilinoCadastroDTO, Integer user_id) {
		Optional<Usuario> usuario = usuarioRepository.findById(user_id);

		if (!usuario.isPresent()) {
			throw new ObjectNotFoundException(
					"Usuário não encontrado com o id: " + user_id + ", tipo: " + Usuario.class.getName());
		}
		inquilinoCadastroDTO.setUsuario(usuario.get());
		Inquilino inquilino = inquilinoRepository.save(modelMapper.map(inquilinoCadastroDTO, Inquilino.class));
		return modelMapper.map(inquilino, InquilinoDTO.class);
	}

	@Override
	public Page<InquilinoDTO> findAllInquilinosByUsuario(Integer usuario_id, Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Inquilino> inquilinosPageable = inquilinoRepository.findByUsuarioId(usuario_id, pageRequest);
		return inquilinosPageable.map(i -> modelMapper.map(i, InquilinoDTO.class));
	}

	@Override
	public Optional<InquilinoDTO> buscar(Integer id, Integer usuario_id) {
		Optional<Inquilino> inquilino = inquilinoRepository.findByIdAndUsuarioId(id, usuario_id);
		Optional<Usuario> usuario = usuarioRepository.findById(usuario_id);

		if (!inquilino.isPresent()) {
			throw new ObjectNotFoundException(
					"Inquilino não encontrado com o id: " + id + ", tipo: " + Inquilino.class.getName());
		}

		if (!usuario.isPresent()) {
			throw new ObjectNotFoundException(
					"Usuário não encontrado com o id: " + usuario_id + ", tipo: " + Usuario.class.getName());
		}

		return Optional.of(modelMapper.map(inquilino.get(), InquilinoDTO.class));
	}

}
