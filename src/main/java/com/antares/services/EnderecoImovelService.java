package com.antares.services;

import java.util.Optional;

import com.antares.dto.endereco.EnderecoImovelDTO;

public interface EnderecoImovelService {
	EnderecoImovelDTO salvar(EnderecoImovelDTO endereco);
	Optional<EnderecoImovelDTO> buscarEnderecoImovelPorId(Integer id);
}
