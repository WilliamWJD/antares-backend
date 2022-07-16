package com.antares.builders;

import com.antares.domain.EnderecoUsuario;

public class EnderecoBuilder {
	private EnderecoUsuario endereco;
	
	private EnderecoBuilder() {
	}
	
	public static EnderecoBuilder umEnderecoUsuario() {
		EnderecoBuilder builder = new EnderecoBuilder();
		builder.endereco = new EnderecoUsuario();
		
		builder.endereco.setEstado("SP");
		builder.endereco.setCidade("Sumaré");
		builder.endereco.setCep("13149072");
		builder.endereco.setLogradouro("R. Denilson treze");
		builder.endereco.setNumero(200);
		
		return builder;
	}
	
	public EnderecoUsuario agora() {
		return endereco;
	}
	
	public EnderecoBuilder comId(Integer id) {
		endereco.setId(1); 
		return this;
	}
}
