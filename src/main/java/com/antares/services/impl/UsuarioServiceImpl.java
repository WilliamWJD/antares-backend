package com.antares.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.antares.domain.Usuario;
import com.antares.dto.UsuarioCadastroDTO;
import com.antares.dto.UsuarioDTO;
import com.antares.repository.UsuarioRepository;
import com.antares.services.UsuarioService;

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
}
