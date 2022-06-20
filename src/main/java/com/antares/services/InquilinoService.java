package com.antares.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.antares.dto.inquilino.InquilinoCadastroDto;
import com.antares.dto.inquilino.InquilinoDTO;

public interface InquilinoService {
	public InquilinoDTO save(InquilinoCadastroDto inquilinoCadastroDTO, Integer user_id);
	public Page<InquilinoDTO> findAllInquilinosByUsuario(Integer user_id, Integer page, Integer linesPerPage, String orderBy, String direction);
	public Optional<InquilinoDTO> buscar(Integer id, Integer usuario_id);
	public void delete(Integer id, Integer usuarioId) throws Exception;
}
