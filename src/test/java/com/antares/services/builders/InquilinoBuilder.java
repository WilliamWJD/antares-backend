package com.antares.services.builders;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.antares.domain.Inquilino;
import com.antares.domain.Usuario;

public class InquilinoBuilder {

	private Inquilino inquilino;
	
	private InquilinoBuilder() {
		
	}
	
	public static InquilinoBuilder umInquilino() throws ParseException {
		InquilinoBuilder builder = new InquilinoBuilder();
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
		
		builder.inquilino = new Inquilino();
		builder.inquilino.setNome("Inquilino 1");
		builder.inquilino.setDataNascimento(formato.parse("1994-09-05"));
		builder.inquilino.setRg("123456789");
		builder.inquilino.setCpf("789456123");
		builder.inquilino.setProfissao("Desenvolvedor backend");
		builder.inquilino.setEstadoCivil("CASADO");
		builder.inquilino.setGenero("MASCULINO");
		builder.inquilino.setEmail("william@email.com.br");		
		
		return builder;
	}
	
	public Inquilino agora() {
		return inquilino;
	}
	
	public InquilinoBuilder comId(Integer id) {
		inquilino.setId(id);
		return this;
	}
	
	public InquilinoBuilder comUsuario(Usuario usuario) {
		inquilino.setUsuario(usuario);
		return this;
	}
}
