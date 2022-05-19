package com.antares.services;

import com.antares.dto.UsuarioCadastroDTO;
import com.antares.dto.UsuarioDTO;

public interface UsuarioService {
	public UsuarioDTO save(UsuarioCadastroDTO usuarioCadastroDTO);
}
