package com.antares.services;

import java.util.List;

import com.antares.dto.InquilinoCadastroDto;
import com.antares.dto.InquilinoDTO;

public interface InquilinoService {
	public InquilinoDTO save(InquilinoCadastroDto inquilinoCadastroDTO, Integer user_id);
	public List<InquilinoDTO> findAllInquilinosByUsuario(Integer user_id);
}
