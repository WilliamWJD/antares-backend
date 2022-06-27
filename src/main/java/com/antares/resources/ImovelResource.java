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

import com.antares.dto.imovel.ImovelDto;
import com.antares.services.ImovelService;

@RestController
@RequestMapping(value = "/imoveis")
public class ImovelResource {
	
	@Autowired
	private ImovelService imovelService;
	
	@PostMapping
	public ResponseEntity<Optional<ImovelDto>> save(@Valid @RequestBody ImovelDto imovelDto, @RequestHeader Integer userId){
		return ResponseEntity.status(HttpStatus.CREATED).body(imovelService.save(imovelDto, userId));
	}
	
	@PutMapping
	public ResponseEntity<Optional<ImovelDto>> update(@Valid @RequestBody ImovelDto imovelDto, @RequestHeader Integer userId){
		return ResponseEntity.status(HttpStatus.CREATED).body(imovelService.save(imovelDto, userId));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<ImovelDto>> buscar(@PathVariable Integer id, @RequestHeader Integer userId){
		return ResponseEntity.status(HttpStatus.OK).body(imovelService.findById(id, userId));
	}
	
	@GetMapping
	public ResponseEntity<List<ImovelDto>> findAll(@RequestHeader Integer userId){
		return ResponseEntity.status(HttpStatus.OK).body(imovelService.findAll(userId));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id, @RequestHeader Integer userId){
		imovelService.delete(id, userId);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
