package com.antares.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antares.domain.Inquilino;
import com.antares.domain.Usuario;
import com.antares.dto.InquilinoNewDto;
import com.antares.repository.InquilinoRepository;
import com.antares.repository.UsuarioRepository;
import com.antares.services.InquilinoService;

@Service
public class InquilinoServiceImpl implements InquilinoService {

	@Autowired
	private InquilinoRepository inquilinoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Inquilino save(Inquilino inquilino) {
		return inquilinoRepository.save(inquilino);
	}

	public Inquilino fromDTO(InquilinoNewDto inquilinoDTO) {
		Optional<Usuario> usuario = usuarioRepository.findById(inquilinoDTO.getUsuario_id());
		
		return new Inquilino(null, inquilinoDTO.getNome(), inquilinoDTO.getDataNascimento(), inquilinoDTO.getRg(),
				inquilinoDTO.getCpf(), inquilinoDTO.getProfissao(), inquilinoDTO.getEstadoCivil(),
				inquilinoDTO.getGenero(), inquilinoDTO.getEmail(), usuario.get());
	}

	@Override
	public List<Inquilino> findAllInquilinosByUsuario(Integer usuario_id) {
		return inquilinoRepository.findInquilinoByUser(usuario_id);
	}

}
