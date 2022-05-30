package com.antares.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.antares.domain.Inquilino;
import com.antares.dto.InquilinoCadastroDto;
import com.antares.dto.InquilinoDTO;

public interface InquilinoService {
	public InquilinoDTO save(InquilinoCadastroDto inquilinoCadastroDTO, Integer user_id);
	public Page<Inquilino> findAllInquilinosByUsuario(Integer user_id, Integer page, Integer linesPerPage, String orderBy, String direction);
	public Optional<Inquilino> buscar(Integer id, Integer usuario_id);
}
