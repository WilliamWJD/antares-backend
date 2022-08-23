package com.antares.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antares.dto.locacao.LocacaoDto;
import com.antares.services.impl.LocacaoServiceImpl;

@RestController
@RequestMapping(value = "/locacoes")
public class LocacaoResource {
	
	@Autowired
	private LocacaoServiceImpl locacaoServiceImpl;
	
	@PostMapping
	public ResponseEntity<LocacaoDto> salvaLocacao(@RequestBody LocacaoDto locacaoDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(locacaoServiceImpl.salvarLocacao(locacaoDto));
	}
}
