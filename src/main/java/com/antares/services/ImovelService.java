package com.antares.services;

import java.util.List;
import java.util.Optional;

import com.antares.dto.imovel.ImovelDto;
import com.antares.dto.imovel.ImovelResponseDto;

public interface ImovelService {
	ImovelResponseDto save(ImovelDto imovelDto);
	Optional<ImovelResponseDto> findById(Integer id);
	List<ImovelResponseDto> findAll();
	void delete(Integer id);
}
