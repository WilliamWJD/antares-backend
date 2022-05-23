package com.antares.services.implementations;

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
		if (usuario.isPresent()) {
			inquilinoCadastroDTO.setUsuario(usuario.get());
			Inquilino inquilino = inquilinoRepository.save(modelMapper.map(inquilinoCadastroDTO, Inquilino.class));
			return modelMapper.map(inquilino, InquilinoDTO.class);
		}
		return null;
	}

	@Override
	public Page<Inquilino> findAllInquilinosByUsuario(Integer usuario_id, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Inquilino> inquilinos = inquilinoRepository.findByUsuarioId(usuario_id, pageRequest);
		return inquilinos;
	}

}
