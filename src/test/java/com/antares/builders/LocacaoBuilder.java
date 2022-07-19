package com.antares.builders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.antares.domain.Imovel;
import com.antares.domain.Inquilino;
import com.antares.domain.Locacao;
import com.antares.domain.Usuario;

public class LocacaoBuilder {
	private Locacao locacao;
	
	private LocacaoBuilder() {
	}
	
	public static LocacaoBuilder umaLocacao() throws ParseException {
		LocacaoBuilder builder = new LocacaoBuilder();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		builder.locacao = new Locacao();
		
		builder.locacao.setDataInicio(formatter.parse("2022-07-05"));
		builder.locacao.setStatus(true);
		
		return builder;
	}
	
	public Locacao agora() {
		return locacao;
	}
	
	public LocacaoBuilder comId(Integer id) {
		locacao.setId(id);
		return this;
	}
	
	public LocacaoBuilder comUsuario(Usuario usuario) {
		locacao.setUsuario(usuario);
		return this;
	}
	
	public LocacaoBuilder comImovel(Imovel imovel) {
		locacao.setImovel(imovel);
		return this;
	}
	
	public LocacaoBuilder comInquilino(Inquilino inquilino) {
		locacao.setInquilino(inquilino);
		return this;
	}
	
	public LocacaoBuilder comDataTerminoContrato(Date dataFim) {
		locacao.setDataFim(dataFim);
		return this;
	}
}
