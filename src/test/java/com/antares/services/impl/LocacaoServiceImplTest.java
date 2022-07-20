package com.antares.services.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.antares.builders.ImovelBuilder;
import com.antares.builders.InquilinoBuilder;
import com.antares.builders.LocacaoBuilder;
import com.antares.builders.UsuarioBuilder;
import com.antares.domain.Imovel;
import com.antares.domain.Inquilino;
import com.antares.domain.Locacao;
import com.antares.domain.Usuario;
import com.antares.dto.locacao.LocacaoDto;
import com.antares.mapper.LocacaoMapper;
import com.antares.repository.LocacaoRepository;
import com.antares.services.exceptions.ValidationException;

@RunWith(SpringRunner.class)
public class LocacaoServiceImplTest {
	
	@Spy
	private ModelMapper mapper;
	
	@Spy
	private LocacaoMapper locacaoMapper;
	
	@Mock
	private LocacaoRepository locacaoRepository;
	
	@Mock
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@InjectMocks
	private LocacaoServiceImpl service;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void deveRealizarUmaLocacaoComSucesso() throws ParseException {
		// cenario
		Usuario usuario = UsuarioBuilder.umUsuario().comId(1).agora();
		Inquilino inquilino = InquilinoBuilder.umInquilino().comId(1).agora();
		Imovel imovel = ImovelBuilder.umImovel().comId(1).agora();

		Locacao locacaoEntrada = LocacaoBuilder.umaLocacao().comInquilino(inquilino)
				.comImovel(imovel).agora();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Locacao locacaoSaida = LocacaoBuilder.umaLocacao().comId(1)
				.comDataTerminoContrato(formatter.parse("2023-07-05")).comUsuario(usuario).comInquilino(inquilino)
				.comImovel(imovel).agora();
		
		when(locacaoRepository.save(Mockito.any())).thenReturn(locacaoSaida);
		when(usuarioServiceImpl.findUserById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
		
		// acao
		service.salvarLocacao(mapper.map(locacaoEntrada, LocacaoDto.class), usuario.getId());
		
		// verificacao
		verify(locacaoRepository, times(1)).save(Mockito.any());
	}
	
	@Test
	public void retornaExcessaoCasoUsuarioNaoExista() throws ParseException {
		// cenario
		Usuario usuario = UsuarioBuilder.umUsuario().comId(1).agora();
		Inquilino inquilino = InquilinoBuilder.umInquilino().comId(1).agora();
		Imovel imovel = ImovelBuilder.umImovel().comId(1).agora();

		Locacao locacaoEntrada = LocacaoBuilder.umaLocacao().comInquilino(inquilino)
				.comImovel(imovel).agora();
		
		when(usuarioServiceImpl.findUserById(Mockito.anyInt())).thenReturn(Optional.empty());
		
		// verificacao
		exception.expect(ValidationException.class);
		exception.expectMessage("Usuario não encontrado");
		
		// acao
		service.salvarLocacao(mapper.map(locacaoEntrada, LocacaoDto.class), usuario.getId());
	}
}
