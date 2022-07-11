package com.antares.services;

import java.util.List;
import java.util.Optional;

import com.antares.dto.imovel.ImovelDto;
import com.antares.dto.imovel.ImovelResponseDto;

public interface ImovelService {
	ImovelResponseDto save(ImovelDto imovelDto, Integer userId);
	Optional<ImovelResponseDto> findById(Integer id, Integer userId);
	List<ImovelResponseDto> findAll(Integer userId);
	void delete(Integer id, Integer userId);
}
