package com.antares.builders;

import com.antares.domain.Inquilino;

public class InquilinoBuilder {
	private Inquilino inquilino;
	
	private InquilinoBuilder() {
	}
	
	public static InquilinoBuilder umInquilino() {
		InquilinoBuilder builder = new InquilinoBuilder();
		builder.inquilino = new Inquilino();
		
		builder.inquilino.setNome("Inquilino 1");
		builder.inquilino.setDataNascimento(null);
		builder.inquilino.setRg("123456789");
		builder.inquilino.setCpf("789456123");
		builder.inquilino.setProfissao("Desenvolvedor");
		builder.inquilino.setEstadoCivil("CASADO");
		builder.inquilino.setGenero("MASCULINO");
		builder.inquilino.setEmail("email@email.com.br");
		
		return builder;
	}
	
	public Inquilino agora() {
		return inquilino;
	}
	
	public InquilinoBuilder comId(Integer id) {
		inquilino.setId(id);
		return this;
	}
}
