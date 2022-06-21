package com.antares.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.antares.dto.inquilino.InquilinoCadastroDto;
import com.antares.dto.inquilino.InquilinoDTO;

public interface InquilinoService {
	public InquilinoDTO save(InquilinoCadastroDto inquilinoCadastroDTO, Integer userId);
	public Page<InquilinoDTO> findAllInquilinosByUsuario(Integer userId, Integer page, Integer linesPerPage, String orderBy, String direction);
	public Optional<InquilinoDTO> buscar(Integer id, Integer userId);
	public void delete(Integer id, Integer usuarioId);
}
