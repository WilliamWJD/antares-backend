package com.antares.resources;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antares.dto.inquilino.InquilinoCadastroDto;
import com.antares.dto.inquilino.InquilinoDTO;
import com.antares.services.impl.InquilinoServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Inquilino", tags = { "Inquilino" })
@RequestMapping(value = "/inquilinos")
public class InquilinoResource {
	
	@Autowired
	private InquilinoServiceImpl inquilinoService;
	
	@ApiOperation(value = "${swagger.api.operation.inquilino.value}", notes = "${swagger.api.operation.inquilino.notes}", tags = {"Inquilino" })
	@PostMapping
	public ResponseEntity<InquilinoDTO> save(@RequestBody InquilinoCadastroDto inquilino){
		InquilinoDTO inqui = inquilinoService.save(inquilino);
		return ResponseEntity.status(HttpStatus.CREATED).body(inqui);
	}
	
	@ApiOperation(value = "${swagger.api.operation.inquilino.atualizar.value}", notes = "${swagger.api.operation.inquilino.atualizar.notes}", tags = {"Inquilino" })
	@PutMapping(value = "/{id}")
	public ResponseEntity<InquilinoDTO> update(@PathVariable Integer id, @RequestBody InquilinoCadastroDto inquilino){
		inquilino.setId(id);
		InquilinoDTO inqui = inquilinoService.save(inquilino);
		return ResponseEntity.status(HttpStatus.CREATED).body(inqui);
	}
	
	@ApiOperation(value = "${swagger.api.operation.inquilino.busca.value}", notes = "${swagger.api.operation.inquilino.busca.notes}", tags = {"Inquilino" })
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<InquilinoDTO>> buscar(@Valid @PathVariable Integer id){
		return ResponseEntity.ok().body(inquilinoService.buscar(id));
	}
	
	@ApiOperation(value = "${swagger.api.operation.inquilino.busca.todos.value}", notes = "${swagger.api.operation.inquilino.busca.todos.notes}", tags = {"Inquilino" })
	@GetMapping(value = "/page")
	public ResponseEntity<Page<InquilinoDTO>> findAllInquilinosByUser(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction
	){
		return ResponseEntity.ok().body(inquilinoService.findAllInquilinosByUsuario(page, linesPerPage, orderBy, direction));
	}
	
	@ApiOperation(value = "${swagger.api.operation.inquilino.delete.value}", notes = "${swagger.api.operation.inquilino.delete.notes}", tags = {"Inquilino" })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		inquilinoService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
 