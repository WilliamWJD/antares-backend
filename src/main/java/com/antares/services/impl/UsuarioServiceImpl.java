package com.antares.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.antares.domain.Usuario;
import com.antares.dto.UsuarioDTO;
import com.antares.dto.UsuarioResponseDTO;
import com.antares.mapper.UsuarioMapper;
import com.antares.repository.UsuarioRepository;
import com.antares.security.UserSS;
import com.antares.services.UsuarioService;
import com.antares.services.exceptions.DataIntegrityViolationException;
import com.antares.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService {	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UsuarioResponseDTO save(UsuarioDTO usuarioCadastroDTO) {
		try {	
			if(usuarioCadastroDTO.getId() != null) {
				findUserById(usuarioCadastroDTO.getId());
			}
			
			String encoderPassword = passwordEncoder.encode(usuarioCadastroDTO.getPassword());
			usuarioCadastroDTO.setPassword(encoderPassword);
			
			Usuario usuario = usuarioRepository.save(usuarioMapper.mapearDtoParaEntity(usuarioCadastroDTO));
			
			return modelMapper.map(usuario, UsuarioResponseDTO.class);
		}catch (Exception e) {
			throw new DataIntegrityViolationException("Ocorreu um erro ao salvar usuário.", e.getCause());
		}
	}

	@Override
	public Optional<Usuario> findUserById(Integer id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (!usuario.isPresent()) {
			throw new ObjectNotFoundException(
					"Usuário não encontrado com o id: " + id + ", tipo: " + Usuario.class.getName());
		}
		return usuario;
	}
	
	public UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
