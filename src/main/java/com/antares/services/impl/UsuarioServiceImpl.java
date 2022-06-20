package com.antares.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.antares.domain.Usuario;
import com.antares.dto.usuario.UsuarioCadastroDTO;
import com.antares.dto.usuario.UsuarioDTO;
import com.antares.repository.UsuarioRepository;
import com.antares.services.UsuarioService;
import com.antares.services.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

	private final UsuarioRepository usuarioRepository;	
	private final ModelMapper modelMapper;
	
	@Override
	public UsuarioDTO save(UsuarioCadastroDTO usuarioCadastroDTO){
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String encoderPassword = passwordEncoder.encode(usuarioCadastroDTO.getPassword());
		usuarioCadastroDTO.setPassword(encoderPassword);
		
		Usuario usuario = usuarioRepository.save(modelMapper.map(usuarioCadastroDTO, Usuario.class));
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

	@Override
	public UsuarioDTO update(Integer id, UsuarioCadastroDTO usuarioCadastroDTO) {
		findUserById(id);
		
		Usuario usuario = usuarioRepository.save(modelMapper.map(usuarioCadastroDTO, Usuario.class));
		
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

	@Override
	public Optional<Usuario> findUserById(Integer id) {		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(!usuario.isPresent()) {
			throw new ObjectNotFoundException(
					"Usuário não encontrado com o id: " + id + ", tipo: " + Usuario.class.getName());
		}
		return usuario;
	}
}
