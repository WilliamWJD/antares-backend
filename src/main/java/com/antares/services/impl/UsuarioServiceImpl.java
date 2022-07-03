package com.antares.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.antares.domain.Usuario;
import com.antares.dto.usuario.UsuarioCadastroDTO;
import com.antares.dto.usuario.UsuarioDTO;
import com.antares.dto.usuario.UsuarioUpdateDTO;
import com.antares.repository.UsuarioRepository;
import com.antares.services.UsuarioService;
import com.antares.services.exceptions.DataIntegrityViolationException;
import com.antares.services.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	private final UsuarioRepository usuarioRepository;
	private final ModelMapper modelMapper;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UsuarioDTO save(UsuarioCadastroDTO usuarioCadastroDTO) {
		try {
			logger.info("** Cadastro de Usuario - Iniciando o cadastro de usuário");
			
			String encoderPassword = passwordEncoder.encode(usuarioCadastroDTO.getPassword());
			usuarioCadastroDTO.setPassword(encoderPassword);
			Usuario usuario = usuarioRepository.save(modelMapper.map(usuarioCadastroDTO, Usuario.class));
			
			logger.info("** Cadastro de Usuario - Finalizando o cadastro de usuário");
			return modelMapper.map(usuario, UsuarioDTO.class);
		}catch (Exception e) {
			logger.info("** Cadastro de Usuario - Ocorreu um erro ao salvar o usuário {}", usuarioCadastroDTO);
			throw new DataIntegrityViolationException("Ocorreu um erro ao salvar usuário.", e.getCause());
		}
	}

	@Override
	public UsuarioDTO update(Integer id, UsuarioUpdateDTO usuarioUpdateDTO) {
		try {
			logger.info("** Atualizacao de Usuario - Iniciando a atualizacao de usuário");
			Optional<Usuario> user = findUserById(id);
			
			logger.info("** Atualizacao de Usuario - Valida se o usuario já existe e altera seus atributos");
			if (user.isPresent()) {
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
			
			logger.info("** Atualizacao de Usuario - Persiste as atualizacoes na base de dados");
			Usuario usuario = usuarioRepository.save(user.get());
			
			logger.info("** Atualizacao de Usuario - Finaliza as alteracoes de usuario");
			return modelMapper.map(usuario, UsuarioDTO.class);
		} catch (Exception e) {
			logger.info("** Atualizacao de Usuario - Ocorreu um erro ao alterar os dados do usuario de id: {}", id);
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
