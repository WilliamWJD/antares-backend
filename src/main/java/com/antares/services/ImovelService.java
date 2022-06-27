package com.antares.services;

import java.util.List;
import java.util.Optional;

import com.antares.dto.imovel.ImovelDto;

public interface ImovelService {
	Optional<ImovelDto> save(ImovelDto imovelDto, Integer userId);
	Optional<ImovelDto> findById(Integer id, Integer userId);
	List<ImovelDto> findAll(Integer userId);
	void delete(Integer id, Integer userId);
}
