package com.antares.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.antares.domain.Inquilino;
import com.antares.dto.InquilinoCadastroDto;
import com.antares.dto.InquilinoDTO;
import com.antares.services.implementations.InquilinoServiceImpl;

@Controller
@RequestMapping(value = "/inquilinos")
public class InquilinoResource {
	
	@Autowired
	private InquilinoServiceImpl inquilinoService;
	
	@PostMapping
	public ResponseEntity<InquilinoDTO> save(@RequestBody InquilinoCadastroDto inquilino, @RequestHeader Integer user_id){
		InquilinoDTO inqui = inquilinoService.save(inquilino, user_id);
		return ResponseEntity.status(HttpStatus.CREATED).body(inqui);
	}
	
	@GetMapping
	public ResponseEntity<List<Inquilino>> findAllInquilinosByUser(@RequestHeader Integer usuario_id){
		List<Inquilino> inquilinos = inquilinoService.findAllInquilinosByUsuario(usuario_id);
		return ResponseEntity.ok().body(inquilinos);
	}
}
