package com.antares.builders;

import com.antares.domain.Imovel;

public class ImovelBuilder {
	private Imovel imovel;
	
	private ImovelBuilder() {
	}
	
	public static ImovelBuilder umImovel() {
		ImovelBuilder builder = new ImovelBuilder();
		builder.imovel = new Imovel();
		
		builder.imovel.setDescricao("Imovel 1");
		builder.imovel.setGaragem(true);
		builder.imovel.setValor(500.0);
		
		return builder;
	}
	
	public Imovel agora() {
		return imovel;
	}
	
	public ImovelBuilder comId(Integer id) {
		imovel.setId(id);
		return this;
	}
}
