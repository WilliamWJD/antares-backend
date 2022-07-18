package com.antares.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.antares.domain.Usuario;
import com.antares.dto.usuario.UsuarioDTO;
import com.antares.dto.usuario.UsuarioResponseDTO;
import com.antares.dto.usuario.UsuarioUpdateDTO;
import com.antares.mapper.UsuarioMapper;
import com.antares.repository.UsuarioRepository;
import com.antares.services.EnderecoUsuarioService;
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
	private EnderecoUsuarioService enderecoService;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UsuarioResponseDTO save(UsuarioDTO usuarioCadastroDTO) {
		try {		
			String encoderPassword = passwordEncoder.encode(usuarioCadastroDTO.getPassword());
			usuarioCadastroDTO.setPassword(encoderPassword);
			
			Usuario usuario = usuarioRepository.save(usuarioMapper.mapearDtoParaEntity(usuarioCadastroDTO));
			
			return modelMapper.map(usuario, UsuarioResponseDTO.class);
		}catch (Exception e) {
			throw new DataIntegrityViolationException("Ocorreu um erro ao salvar usuário.", e.getCause());
		}
	}

	@Override
	public UsuarioResponseDTO update(Integer id, UsuarioUpdateDTO usuarioUpdateDTO) {
		try {
			Optional<Usuario> user = findUserById(id);
			
			if (!user.isPresent()) {
				throw new ObjectNotFoundException("Usuário não encontrado");
			}
			
			usuarioUpdateDTO.setId(id);
			usuarioUpdateDTO.setPassword(user.get().getPassword());
			
			Usuario usuario = usuarioRepository.save(modelMapper.map(usuarioUpdateDTO, Usuario.class));
			
			enderecoService.salvar(usuarioUpdateDTO.getEnderecos(), usuario);
			
			return modelMapper.map(usuario, UsuarioResponseDTO.class);
		} catch (Exception e) {
			throw new DataIntegrityViolationException("Ocorreu um erro ao atualizar esse usuário.", e.getCause());
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
}
