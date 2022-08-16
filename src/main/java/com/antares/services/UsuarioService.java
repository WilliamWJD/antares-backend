package com.antares.services;

import java.util.Optional;

import com.antares.domain.Usuario;
import com.antares.dto.usuario.UsuarioDTO;
import com.antares.dto.usuario.UsuarioResponseDTO;
import com.antares.security.UserSS;

public interface UsuarioService {
	UsuarioResponseDTO save(UsuarioDTO usuarioCadastroDTO);
	Optional<Usuario> findUserById(Integer id);
	public UserSS authenticated();
}
