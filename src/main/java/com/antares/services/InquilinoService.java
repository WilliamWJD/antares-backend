package com.antares.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.antares.dto.inquilino.InquilinoCadastroDto;
import com.antares.dto.inquilino.InquilinoDTO;

public interface InquilinoService {
	InquilinoDTO save(InquilinoCadastroDto inquilinoCadastroDTO, Integer userId);
	Page<InquilinoDTO> findAllInquilinosByUsuario(Integer userId, Integer page, Integer linesPerPage, String orderBy, String direction);
	Optional<InquilinoDTO> buscar(Integer id, Integer userId);
	void delete(Integer id, Integer usuarioId);
}
