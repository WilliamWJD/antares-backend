package com.antares.services;

import java.util.Optional;

import com.antares.dto.ImovelDto;

public interface ImovelService {
	public Optional<ImovelDto> save(ImovelDto imovelDto, Integer user_id);
}
