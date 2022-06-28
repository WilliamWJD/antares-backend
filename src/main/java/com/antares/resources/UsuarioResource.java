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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/usuarios")
@Api(value = "Usuário", tags = { "Usuário" })
public class UsuarioResource {

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@ApiOperation(value = "${swagger.api.operation.usuario.value}", notes = "${swagger.api.operation.usuario.notes}", tags = {"Usuário" })
	@PostMapping
	public ResponseEntity<UsuarioDTO> save(@RequestBody UsuarioCadastroDTO usuario) {
		UsuarioDTO obj = usuarioService.save(usuario);
		return ResponseEntity.ok().body(obj);
	}
}
