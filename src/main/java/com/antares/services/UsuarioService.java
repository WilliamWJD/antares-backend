package com.antares.services;

import java.util.Optional;

import com.antares.domain.Usuario;
import com.antares.dto.UsuarioCadastroDTO;
import com.antares.dto.UsuarioDTO;

public interface UsuarioService {
	public UsuarioDTO save(UsuarioCadastroDTO usuarioCadastroDTO);
	public UsuarioDTO update(Integer id, UsuarioCadastroDTO usuarioCadastroDTO);
	public Optional<Usuario> findUserById(Integer id);
}
