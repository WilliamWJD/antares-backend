package com.antares.services;

import java.util.Optional;

import com.antares.domain.Usuario;
import com.antares.dto.usuario.UsuarioCadastroDTO;
import com.antares.dto.usuario.UsuarioDTO;
import com.antares.dto.usuario.UsuarioUpdateDTO;

public interface UsuarioService {
	UsuarioDTO save(UsuarioCadastroDTO usuarioCadastroDTO);
	UsuarioDTO update(Integer id, UsuarioUpdateDTO usuarioUpdateDTO);
	Optional<Usuario> findUserById(Integer id);
}
