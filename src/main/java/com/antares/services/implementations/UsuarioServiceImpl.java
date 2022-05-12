package com.antares.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.antares.domain.Usuario;
import com.antares.dto.UsuarioNewDTO;
import com.antares.repository.UsuarioRepository;
import com.antares.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private PasswordEncoder passwordEncoder;
	
	public Usuario save(Usuario usuario) {
		this.passwordEncoder = new BCryptPasswordEncoder();
		String encoderPassword = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(encoderPassword);
		return usuarioRepository.save(usuario);
	}

	public Usuario fromDTO(UsuarioNewDTO usuarioDTO) {
		return new Usuario(null, usuarioDTO.getNome(), usuarioDTO.getDataNascimento(), usuarioDTO.getRg(),
				usuarioDTO.getCpf(), usuarioDTO.getProfissao(), usuarioDTO.getEstadoCivil(), usuarioDTO.getGenero(),
				usuarioDTO.getEmail(), usuarioDTO.getPassword());
	}
}
