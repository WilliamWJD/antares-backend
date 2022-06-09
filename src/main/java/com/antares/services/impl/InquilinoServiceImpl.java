package com.antares.services.impl;

import java.util.Objects;
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
import com.antares.repository.InquilinoRepositoryImpl;
import com.antares.repository.UsuarioRepositoryImpl;
import com.antares.services.InquilinoService;
import com.antares.services.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InquilinoServiceImpl implements InquilinoService {

	private final InquilinoRepositoryImpl inquilinoRepository;
	private final UsuarioRepositoryImpl usuarioRepository;
	private final ModelMapper modelMapper;

	@Override
	public InquilinoDTO save(InquilinoCadastroDto inquilinoCadastroDTO, Integer user_id) {
		Optional<Usuario> usuario = usuarioRepository.findById(user_id);
		if (usuario.isPresent()) {
			inquilinoCadastroDTO.setUsuario(usuario.get());
			Inquilino inquilino = inquilinoRepository.save(modelMapper.map(inquilinoCadastroDTO, Inquilino.class));
			return modelMapper.map(inquilino, InquilinoDTO.class);
		}
		return null;
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

		if (Objects.isNull(inquilino)) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Inquilino.class.getName());
		}
		
		return Optional.of(modelMapper.map(inquilino, InquilinoDTO.class));
	}

}
