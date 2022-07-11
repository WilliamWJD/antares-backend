package com.antares.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antares.domain.Imovel;
import com.antares.domain.Usuario;
import com.antares.dto.imovel.ImovelDto;
import com.antares.dto.imovel.ImovelResponseDto;
import com.antares.dto.usuario.UsuarioDTO;
import com.antares.mapper.ImovelMapper;
import com.antares.repository.ImovelRepository;
import com.antares.services.ImovelService;
import com.antares.services.UsuarioService;
import com.antares.services.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImovelServiceImpl implements ImovelService {

	@Autowired
	private ImovelRepository imovelRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ImovelMapper imovelMapper;

	@Override
	public ImovelResponseDto save(ImovelDto imovelDto, Integer userId) {
		Optional<Usuario> usuario = usuarioService.findUserById(userId);
		
		// verifica se o usuário da requisição existe
		if(!usuario.isPresent()) {
			 throw new ObjectNotFoundException("Não foi possível encontrar um imóvel com id: "+imovelDto.getId()+" Tipo: "+Imovel.class.getName());
		}
		
		imovelDto.setUsuario(modelMapper.map(usuario.get(), UsuarioDTO.class));
		Imovel imovel = imovelRepository.save(imovelMapper.mapearDtoParaEntity(imovelDto));
		return modelMapper.map(imovel, ImovelResponseDto.class);
	}

	@Override
	public Optional<ImovelResponseDto> findById(Integer id, Integer userId) {
		usuarioService.findUserById(userId);
		Optional<Imovel> imovel = imovelRepository.findByIdAndUsuarioId(id, userId);
		if(!imovel.isPresent()) {
			 throw new ObjectNotFoundException("Não foi possível encontrar um imóvel com id: "+id+" Tipo: "+Imovel.class.getName());
		}
		return Optional.of(modelMapper.map(imovel.get(), ImovelResponseDto.class));
	}

	@Override
	public List<ImovelResponseDto> findAll(Integer userId) {
		usuarioService.findUserById(userId);
		List<Imovel> imoveis = imovelRepository.findAllByUsuarioId(userId);
		return imoveis.stream().map(imovel -> modelMapper.map(imovel, ImovelResponseDto.class)).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id, Integer userId) {
		usuarioService.findUserById(userId);
		imovelRepository.deleteImovelByIdAndUsuarioId(id, userId);
	}

}
