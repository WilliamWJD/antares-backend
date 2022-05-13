package com.antares.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.antares.domain.Inquilino;
import com.antares.dto.InquilinoNewDto;
import com.antares.services.implementations.InquilinoServiceImpl;

@Controller
@RequestMapping(value = "/inquilinos")
public class InquilinoResource {
	
	@Autowired
	private InquilinoServiceImpl inquilinoService;
	
	@PostMapping
	public ResponseEntity<Inquilino> save(@RequestBody InquilinoNewDto inquilino, @RequestHeader Integer user_id){
		inquilino.setUsuario_id(user_id);
		Inquilino obj = inquilinoService.fromDTO(inquilino);
		inquilinoService.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
