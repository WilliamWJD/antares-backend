package com.antares.builders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.antares.domain.EnderecoUsuario;
import com.antares.domain.Usuario;

public class UsuarioBuilder {
	
	private Usuario usuario;
	
	private UsuarioBuilder() {
		
	}
	
	public static UsuarioBuilder umUsuario() throws ParseException {
		UsuarioBuilder builder = new UsuarioBuilder();
		builder.usuario = new Usuario();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		builder.usuario.setNome("Usuario 1");
		builder.usuario.setDataNascimento(formatter.parse("1994-09-05"));
		builder.usuario.setRg("123456789");
		builder.usuario.setCpf("789456123");
		builder.usuario.setProfissao("Analista de Sistema");
		builder.usuario.setEstadoCivil("CASADO");
		builder.usuario.setGenero("MASCULINO");
		builder.usuario.setEmail("william@gmail.com.br");
		builder.usuario.setPassword("456789");
		builder.usuario.setEnderecos(null);
		
		return builder;
	}
	
	public Usuario agora() {
		return usuario;
	}
	
	public UsuarioBuilder comEnderecos(List<EnderecoUsuario> enderecos) {
		usuario.setEnderecos(enderecos);
		return this;
	}
	
	public UsuarioBuilder comId(Integer id) {
		usuario.setId(1);
		return this;
	}
}
