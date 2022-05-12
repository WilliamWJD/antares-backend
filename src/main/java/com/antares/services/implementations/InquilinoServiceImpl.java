package com.antares.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antares.domain.Inquilino;
import com.antares.dto.InquilinoNewDto;
import com.antares.repository.InquilinoRepository;
import com.antares.services.InquilinoService;

@Service
public class InquilinoServiceImpl implements InquilinoService {

	@Autowired
	private InquilinoRepository inquilinoRepository;

	@Override
	public Inquilino save(Inquilino inquilino) {
		return inquilinoRepository.save(inquilino);
	}

	public Inquilino fromDTO(InquilinoNewDto inquilinoDTO) {
		return new Inquilino(null, inquilinoDTO.getNome(), inquilinoDTO.getDataNascimento(), inquilinoDTO.getRg(),
				inquilinoDTO.getCpf(), inquilinoDTO.getProfissao(), inquilinoDTO.getEstadoCivil(),
				inquilinoDTO.getGenero(), inquilinoDTO.getEmail());
	}

}
