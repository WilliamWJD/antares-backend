package com.antares.services;

import java.util.Optional;

import com.antares.dto.EnderecoImovelDTO;

public interface EnderecoImovelService {
	EnderecoImovelDTO salvar(EnderecoImovelDTO endereco, Integer userId);
	Optional<EnderecoImovelDTO> buscarEnderecoImovelPorId(Integer id);
	EnderecoImovelDTO buscarEnderecoImovelPorCepENumero(String cep, Integer numero, Integer userId);
}
