package com.antares.services;

import java.util.List;

import com.antares.domain.Usuario;
import com.antares.dto.endereco.EnderecoDTO;

public interface EnderecoService {
	List<EnderecoDTO> salvar(List<EnderecoDTO> enderecos, Usuario usuario);
}
