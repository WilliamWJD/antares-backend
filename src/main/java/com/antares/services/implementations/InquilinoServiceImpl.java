package com.antares.services.implementations;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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
		inquilinoCadastroDTO.setUsuario(usuario.get());
		Inquilino inquilino = inquilinoRepository.save(modelMapper.map(inquilinoCadastroDTO, Inquilino.class));
		return modelMapper.map(inquilino, InquilinoDTO.class);
	}

	@Override
	public List<Inquilino> findAllInquilinosByUsuario(Integer usuario_id) {
		return inquilinoRepository.findInquilinoByUser(usuario_id);
	}

}
