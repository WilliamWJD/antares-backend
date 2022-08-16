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
import com.antares.security.UserSS;
import com.antares.services.InquilinoService;
import com.antares.services.UsuarioService;

import com.antares.services.exceptions.DataIntegrityViolationException;
import com.antares.services.exceptions.ObjectNotFoundException;
import com.antares.services.exceptions.ValidationException;

@Service
public class InquilinoServiceImpl implements InquilinoService {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private InquilinoRepository inquilinoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public InquilinoDTO save(InquilinoCadastroDto inquilinoCadastroDTO) {
		try {
			UserSS user = usuarioService.authenticated();
			
			if(user == null) {
				throw new ValidationException("Acesso negado");
			}
			
			Optional<Usuario> usuario = usuarioService.findUserById(user.getId());

			if (inquilinoCadastroDTO.getId() != null) {
				buscar(inquilinoCadastroDTO.getId());
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
	public Page<InquilinoDTO> findAllInquilinosByUsuario(Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		UserSS user = usuarioService.authenticated();
		if(user == null) {
			throw new ValidationException("Acesso negado");
		}
		Optional<Usuario> usuario = usuarioService.findUserById(user.getId());
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Inquilino> inquilinosPageable = inquilinoRepository.findByUsuarioId(usuario.get().getId(), pageRequest);
		return inquilinosPageable.map(i -> modelMapper.map(i, InquilinoDTO.class));
	}

	@Override
	public Optional<InquilinoDTO> buscar(Integer id) {
		UserSS user = usuarioService.authenticated();
		if(user == null) {
			throw new ValidationException("Acesso negado");
		}
		Optional<Usuario> usuario = usuarioService.findUserById(user.getId());
		
		Optional<Inquilino> inquilino = inquilinoRepository.findByIdAndUsuarioId(id, usuario.get().getId());

		if (!inquilino.isPresent()) {
			throw new ObjectNotFoundException(
					"Inquilino não encontrado com o id: " + id + ", tipo: " + Inquilino.class.getName());
		}

		return Optional.of(modelMapper.map(inquilino.get(), InquilinoDTO.class));
	}

	@Override
	public void delete(Integer id) {		
		Optional<InquilinoDTO> inquilino = buscar(id);
		
		if(!inquilino.isPresent()) {
			throw new ObjectNotFoundException("Inquilino não encontrado com o id: " + id + ", tipo: " + Inquilino.class.getName());
		}
		
		inquilinoRepository.delete(modelMapper.map(inquilino.get(), Inquilino.class));
	}

}
