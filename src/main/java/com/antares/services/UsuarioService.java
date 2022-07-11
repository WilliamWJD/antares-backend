package com.antares.services;

import java.util.Optional;

import com.antares.domain.Usuario;
import com.antares.dto.usuario.UsuarioDTO;
import com.antares.dto.usuario.UsuarioResponseDTO;
import com.antares.dto.usuario.UsuarioUpdateDTO;

public interface UsuarioService {
	UsuarioResponseDTO save(UsuarioDTO usuarioCadastroDTO);
	UsuarioResponseDTO update(Integer id, UsuarioUpdateDTO usuarioUpdateDTO);
	Optional<Usuario> findUserById(Integer id);
}
