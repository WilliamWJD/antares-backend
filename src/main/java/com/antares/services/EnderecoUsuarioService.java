package com.antares.services;

import java.util.List;

import com.antares.domain.Usuario;
import com.antares.dto.endereco.EnderecoUsuarioDTO;

public interface EnderecoUsuarioService {
	List<EnderecoUsuarioDTO> salvar(List<EnderecoUsuarioDTO> enderecos, Usuario usuario);
}
