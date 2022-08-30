package com.antares.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antares.dto.ImovelDto;
import com.antares.dto.ImovelResponseDto;
import com.antares.services.ImovelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Imóvel", tags = { "Imóvel" })
@RequestMapping(value = "/imoveis")
public class ImovelResource {
	
	@Autowired
	private ImovelService imovelService;
	
	@ApiOperation(value = "${swagger.api.operation.imovel.value}", notes = "${swagger.api.operation.imovel.notes}", tags = {"Imóvel" })
	@PostMapping
	public ResponseEntity<ImovelResponseDto> save(@Valid @RequestBody ImovelDto imovelDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(imovelService.save(imovelDto));
	}
	
	@ApiOperation(value = "${swagger.api.operation.imovel.update.value}", notes = "${swagger.api.operation.imovel.update.notes}", tags = {"Imóvel" })
	@PutMapping
	public ResponseEntity<ImovelResponseDto> update(@Valid @RequestBody ImovelDto imovelDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(imovelService.save(imovelDto));
	}
	
	@ApiOperation(value = "${swagger.api.operation.imovel.buscar.value}", notes = "${swagger.api.operation.imovel.buscar.notes}", tags = {"Imóvel" })
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<ImovelResponseDto>> buscar(@PathVariable Integer id){
		return ResponseEntity.status(HttpStatus.OK).body(imovelService.findById(id));
	}
	
	@ApiOperation(value = "${swagger.api.operation.imovel.buscar.todos.value}", notes = "${swagger.api.operation.imovel.buscar.todos.notes}", tags = {"Imóvel" })
	@GetMapping
	public ResponseEntity<List<ImovelResponseDto>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(imovelService.findAll());
	}
	
	@ApiOperation(value = "${swagger.api.operation.imovel.delete.value}", notes = "${swagger.api.operation.imovel.delete.notes}", tags = {"Imóvel" })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		imovelService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
