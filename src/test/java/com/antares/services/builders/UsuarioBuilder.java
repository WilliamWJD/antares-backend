package com.antares.services.builders;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.antares.domain.Usuario;

public class UsuarioBuilder {

	private Usuario usuario;
	
	private UsuarioBuilder() {}
	
	public static UsuarioBuilder umUsuario() throws ParseException {
		UsuarioBuilder builder = new UsuarioBuilder();
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
		
		builder.usuario = new Usuario();
		builder.usuario.setNome("Usuario 1");
		builder.usuario.setDataNascimento(formato.parse("1994-09-05"));
		builder.usuario.setRg("123456789");
		builder.usuario.setCpf("789456123");
		builder.usuario.setProfissao("Desenvolvedor backend");
		builder.usuario.setEstadoCivil("CASADO");
		builder.usuario.setGenero("MASCULINO");
		builder.usuario.setEmail("william@email.com.br");		
		builder.usuario.setPassword("123456789");	
		
		return builder;
	}
	
	public Usuario agora() {
		return usuario;
	}
	
	public UsuarioBuilder comId(Integer id) {
		usuario.setId(id);
		return this;
	}
}
