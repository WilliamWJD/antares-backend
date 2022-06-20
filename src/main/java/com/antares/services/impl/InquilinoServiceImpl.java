package com.antares.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.antares.domain.Inquilino;
import com.antares.domain.Usuario;
import com.antares.dto.inquilino.InquilinoCadastroDto;
import com.antares.dto.inquilino.InquilinoDTO;
import com.antares.repository.InquilinoRepository;
import com.antares.services.InquilinoService;
import com.antares.services.UsuarioService;
import com.antares.services.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InquilinoServiceImpl implements InquilinoService {

	@Autowired
	UsuarioService usuarioService;

	private final InquilinoRepository inquilinoRepository;
	private final ModelMapper modelMapper;

	@Override
	public InquilinoDTO save(InquilinoCadastroDto inquilinoCadastroDTO, Integer user_id) {
		Optional<Usuario> usuario = usuarioService.findUserById(user_id);

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

		usuarioService.findUserById(usuario_id);

		if (!inquilino.isPresent()) {
			throw new ObjectNotFoundException(
					"Inquilino não encontrado com o id: " + id + ", tipo: " + Inquilino.class.getName());
		}

		return Optional.of(modelMapper.map(inquilino.get(), InquilinoDTO.class));
	}

	@Override
	public void delete(Integer id, Integer usuarioId) {
		usuarioService.findUserById(usuarioId);
		inquilinoRepository.deleteByIdAndUsuarioId(id, usuarioId);
	}

}
