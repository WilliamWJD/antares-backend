package com.antares.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antares.domain.EnderecoImovel;
import com.antares.dto.endereco.EnderecoImovelDTO;
import com.antares.repository.EnderecoImovelRepository;
import com.antares.services.EnderecoImovelService;
import com.antares.services.exceptions.ValidationException;

@Service
public class EnderecoImovelServiceImpl implements EnderecoImovelService{
	
	@Autowired
	private EnderecoImovelRepository enderecoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public EnderecoImovelDTO salvar(EnderecoImovelDTO endereco, Integer userId) {
		Optional<EnderecoImovelDTO> enderecoExistente = buscarEnderecoImovelPorCepENumero(endereco.getCep(), endereco.getNumero(), userId);
		
		if(enderecoExistente.isPresent() && !enderecoExistente.get().getId().equals(endereco.getId())) {
			throw new ValidationException("Endereço já cadastrar com o cep: "+endereco.getCep()+" e número: "+endereco.getNumero());
		}
		
		EnderecoImovel enderecoImovel = enderecoRepository.save(modelMapper.map(endereco, EnderecoImovel.class));
		return modelMapper.map(enderecoImovel, EnderecoImovelDTO.class);
	}

	@Override
	public Optional<EnderecoImovelDTO> buscarEnderecoImovelPorId(Integer id) {
		Optional<EnderecoImovel> enderecoImovel = enderecoRepository.findById(id);
		if(!enderecoImovel.isPresent()) {
			throw new ValidationException("Endereco não encontrado com o id: "+id);
		}
		return Optional.of(modelMapper.map(enderecoImovel, EnderecoImovelDTO.class));
	}

	@Override
	public EnderecoImovelDTO buscarEnderecoImovelPorCepENumero(String cep, Integer numero, Integer userId) {
		EnderecoImovel enderecoImovel = enderecoRepository.buscarEnderecoImovelPorNumeroCepImovelId(cep, numero, userId);
		if(enderecoImovel == null) {
			return null;
		}
		return modelMapper.map(enderecoImovel, EnderecoImovelDTO.class);
	}
}
