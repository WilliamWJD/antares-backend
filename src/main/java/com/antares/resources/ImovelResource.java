package com.antares.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antares.dto.ImovelDto;
import com.antares.services.ImovelService;

@RestController
@RequestMapping(value = "/imoveis")
public class ImovelResource {
	
	@Autowired
	private ImovelService imovelService;
	
	@PostMapping
	public ResponseEntity<Optional<ImovelDto>> save(@RequestBody ImovelDto imovelDto, @RequestHeader Integer user_id){
		return ResponseEntity.status(HttpStatus.CREATED).body(imovelService.save(imovelDto, user_id));
	}
	
	@PutMapping
	public ResponseEntity<Optional<ImovelDto>> update(@RequestBody ImovelDto imovelDto, @RequestHeader Integer user_id){
		return ResponseEntity.status(HttpStatus.CREATED).body(imovelService.save(imovelDto, user_id));
	}
}
