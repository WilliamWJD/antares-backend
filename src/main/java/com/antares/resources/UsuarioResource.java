package com.antares.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.antares.domain.Usuario;
import com.antares.dto.UsuarioCadastroDTO;
import com.antares.dto.UsuarioDTO;
import com.antares.services.implementations.UsuarioServiceImpl;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> save(@RequestBody UsuarioCadastroDTO usuario){
		UsuarioDTO obj = usuarioService.save(usuario);
		usuarioService.save(usuario);
		return ResponseEntity.ok().body(obj);
	}
}
