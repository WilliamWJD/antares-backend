package com.antares.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.antares.dto.InquilinoCadastroDto;
import com.antares.dto.InquilinoDTO;

public interface InquilinoService {
	InquilinoDTO save(InquilinoCadastroDto inquilinoCadastroDTO);
	Page<InquilinoDTO> findAllInquilinosByUsuario(Integer page, Integer linesPerPage, String orderBy, String direction);
	Optional<InquilinoDTO> buscar(Integer id);
	void delete(Integer id);
}
