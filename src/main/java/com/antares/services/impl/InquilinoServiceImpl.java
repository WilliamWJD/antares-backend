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

import com.antares.services.exceptions.DataIntegrityViolationException;
import com.antares.services.exceptions.ObjectNotFoundException;

@Service
public class InquilinoServiceImpl implements InquilinoService {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	private InquilinoRepository inquilinoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public InquilinoDTO save(InquilinoCadastroDto inquilinoCadastroDTO, Integer userId) {
		try {
			Optional<Usuario> usuario = usuarioService.findUserById(userId);

			if (inquilinoCadastroDTO.getId() != null) {
				buscar(inquilinoCadastroDTO.getId(), userId);
			}

			if(usuario.isPresent()) {
				inquilinoCadastroDTO.setUsuario(usuario.get());
			}

			Inquilino inquilino = inquilinoRepository.save(modelMapper.map(inquilinoCadastroDTO, Inquilino.class));
			return modelMapper.map(inquilino, InquilinoDTO.class);
		} catch (Exception e) {
			throw new DataIntegrityViolationException("Ocorreu um erro ao salvar esse inquilino.", e.getCause());
		}
	}

	@Override
	public Page<InquilinoDTO> findAllInquilinosByUsuario(Integer userId, Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Inquilino> inquilinosPageable = inquilinoRepository.findByUsuarioId(userId, pageRequest);
		return inquilinosPageable.map(i -> modelMapper.map(i, InquilinoDTO.class));
	}

	@Override
	public Optional<InquilinoDTO> buscar(Integer id, Integer userId) {
		Optional<Inquilino> inquilino = inquilinoRepository.findByIdAndUsuarioId(id, userId);

		usuarioService.findUserById(userId);

		if (!inquilino.isPresent()) {
			throw new ObjectNotFoundException(
					"Inquilino não encontrado com o id: " + id + ", tipo: " + Inquilino.class.getName());
		}

		return Optional.of(modelMapper.map(inquilino.get(), InquilinoDTO.class));
	}

	@Override
	public void delete(Integer id, Integer usuarioId) {
		usuarioService.findUserById(usuarioId);
		buscar(id, usuarioId);
		inquilinoRepository.deleteByIdAndUsuarioId(id, usuarioId);
	}

}
