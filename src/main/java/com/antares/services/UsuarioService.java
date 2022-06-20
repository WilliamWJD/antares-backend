package com.antares.services;

import java.util.Optional;

import com.antares.domain.Usuario;
import com.antares.dto.usuario.UsuarioCadastroDTO;
import com.antares.dto.usuario.UsuarioDTO;

public interface UsuarioService {
	public UsuarioDTO save(UsuarioCadastroDTO usuarioCadastroDTO);
	public UsuarioDTO update(Integer id, UsuarioCadastroDTO usuarioCadastroDTO);
	public Optional<Usuario> findUserById(Integer id);
}
