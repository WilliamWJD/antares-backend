package com.antares.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.antares.dto.usuario.UsuarioCadastroDTO;
import com.antares.dto.usuario.UsuarioDTO;
import com.antares.services.impl.UsuarioServiceImpl;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@PostMapping
	public ResponseEntity<UsuarioDTO> save(@RequestBody UsuarioCadastroDTO usuario) {
		UsuarioDTO obj = usuarioService.save(usuario);
		return ResponseEntity.ok().body(obj);
	}
}
