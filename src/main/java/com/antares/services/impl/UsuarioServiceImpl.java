package com.antares.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.antares.domain.Usuario;
import com.antares.dto.usuario.UsuarioCadastroDTO;
import com.antares.dto.usuario.UsuarioDTO;
import com.antares.dto.usuario.UsuarioUpdateDTO;
import com.antares.repository.UsuarioRepository;
import com.antares.services.UsuarioService;
import com.antares.services.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

	private final UsuarioRepository usuarioRepository;	
	private final ModelMapper modelMapper;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public UsuarioDTO save(UsuarioCadastroDTO usuarioCadastroDTO){
		String encoderPassword = passwordEncoder.encode(usuarioCadastroDTO.getPassword());
		usuarioCadastroDTO.setPassword(encoderPassword);
		
		Usuario usuario = usuarioRepository.save(modelMapper.map(usuarioCadastroDTO, Usuario.class));
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

	@Override
	public UsuarioDTO update(Integer id, UsuarioUpdateDTO usuarioUpdateDTO) {
		Optional<Usuario> user = findUserById(id);
		
		if(user.isPresent()) {
			user.get().setNome(usuarioUpdateDTO.getNome());
			user.get().setDataNascimento(usuarioUpdateDTO.getDataNascimento());
			user.get().setDataNascimento(usuarioUpdateDTO.getDataNascimento());
			user.get().setRg(usuarioUpdateDTO.getRg());
			user.get().setCpf(usuarioUpdateDTO.getCpf());
			user.get().setProfissao(usuarioUpdateDTO.getProfissao());
			user.get().setEstadoCivil(usuarioUpdateDTO.getEstadoCivil());
			user.get().setGenero(usuarioUpdateDTO.getGenero());
			user.get().setEmail(usuarioUpdateDTO.getEmail());
		}
		
		Usuario usuario = usuarioRepository.save(user.get());
		
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
