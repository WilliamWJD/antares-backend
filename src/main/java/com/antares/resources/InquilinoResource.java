package com.antares.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public ResponseEntity<InquilinoDTO> save(@Valid @RequestBody InquilinoCadastroDto inquilino, @RequestHeader Integer user_id){
		InquilinoDTO inqui = inquilinoService.save(inquilino, user_id);
		return ResponseEntity.status(HttpStatus.CREATED).body(inqui);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<?> findAllInquilinosByUser(
			@RequestHeader Integer usuario_id, 
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction
	){
		return ResponseEntity.ok().body(inquilinoService.findAllInquilinosByUsuario(usuario_id, page, linesPerPage, orderBy, direction));
	}
}
 