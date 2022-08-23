package com.antares.services.impl;

import static org.mockito.Mockito.doReturn;
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
import com.antares.dto.imovel.ImovelResponseDto;
import com.antares.dto.locacao.LocacaoDto;
import com.antares.mapper.LocacaoMapper;
import com.antares.repository.LocacaoRepository;
import com.antares.services.exceptions.ObjectNotFoundException;
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

	@Mock
	private ImovelServiceImpl imovelServiceImpl;

	@InjectMocks @Spy
	private LocacaoServiceImpl service;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void deveRealizarUmaLocacaoComSucesso() throws ParseException {
		// cenario
		Usuario usuario = UsuarioBuilder.umUsuario().comId(1).agora();
		Inquilino inquilino = InquilinoBuilder.umInquilino().comId(1).agora();

		Imovel imovel = ImovelBuilder.umImovel().comId(1).agora();
		ImovelResponseDto imovelResponse = mapper.map(imovel, ImovelResponseDto.class);

		Locacao locacaoEntrada = LocacaoBuilder.umaLocacao().comInquilino(inquilino).comImovel(imovel).agora();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Locacao locacaoSaida = LocacaoBuilder.umaLocacao().comId(1)
				.comDataTerminoContrato(formatter.parse("2023-07-05")).comUsuario(usuario).comInquilino(inquilino)
				.comImovel(imovel).agora();

		when(locacaoRepository.save(Mockito.any())).thenReturn(locacaoSaida);
		when(usuarioServiceImpl.findUserById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
		when(imovelServiceImpl.findById(Mockito.anyInt())).thenReturn(Optional.of(imovelResponse));

		// acao
		service.salvarLocacao(mapper.map(locacaoEntrada, LocacaoDto.class));

		// verificacao
		verify(locacaoRepository, times(1)).save(Mockito.any());
	}

	@Test
	public void retornaExcessaoCasoUsuarioNaoExista() throws ParseException {
		// cenario
		Inquilino inquilino = InquilinoBuilder.umInquilino().comId(1).agora();
		Imovel imovel = ImovelBuilder.umImovel().comId(1).agora();

		Locacao locacaoEntrada = LocacaoBuilder.umaLocacao().comInquilino(inquilino).comImovel(imovel).agora();

		when(usuarioServiceImpl.findUserById(Mockito.anyInt())).thenReturn(Optional.empty());

		// verificacao
		exception.expect(ValidationException.class);
		exception.expectMessage("Usuario não encontrado");

		// acao
		service.salvarLocacao(mapper.map(locacaoEntrada, LocacaoDto.class));
	}

	@Test
	public void naoDeveRealizarLocacaoCasoImovelEstejaLocado() throws ParseException {
		// cenario
		Usuario usuario = UsuarioBuilder.umUsuario().comId(1).agora();
		Inquilino inquilino = InquilinoBuilder.umInquilino().comId(1).agora();
		Imovel imovel = ImovelBuilder.umImovel().comId(1).agora();

		Locacao locacaoEntrada = LocacaoBuilder.umaLocacao().comInquilino(inquilino).comImovel(imovel).agora();
		Locacao locacaoSaida = LocacaoBuilder.umaLocacao().comId(1).comInquilino(inquilino).comImovel(imovel).agora();

		when(usuarioServiceImpl.findUserById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
		when(locacaoRepository.verificaSeImovelLocado(Mockito.anyInt(), Mockito.anyInt())).thenReturn(locacaoSaida);

		// validacao
		exception.expect(ValidationException.class);
		exception.expectMessage("Esse imovel está com uma locacão ativa.");

		service.salvarLocacao(mapper.map(locacaoEntrada, LocacaoDto.class));

		// validacao
		verify(locacaoRepository, times(1)).save(Mockito.any());
	}

	@Test
	public void deveRealizarARenovacaoDoContrato() throws ParseException {
		// cenario
		Usuario usuario = UsuarioBuilder.umUsuario().comId(1).agora();
		Inquilino inquilino = InquilinoBuilder.umInquilino().comId(1).agora();
		
		Imovel imovel = ImovelBuilder.umImovel().comId(1).agora();
		ImovelResponseDto imovelResponse = mapper.map(imovel, ImovelResponseDto.class);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Locacao locacaoEntrada = LocacaoBuilder.umaLocacao().comInquilino(inquilino).comImovel(imovel).agora();
		Locacao locacao = LocacaoBuilder.umaLocacao().comId(1).comDataTerminoContrato(formatter.parse("2023-05-07")).comInquilino(inquilino).comImovel(imovel).agora();
		
		when(usuarioServiceImpl.findUserById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
		when(imovelServiceImpl.findById(Mockito.anyInt())).thenReturn(Optional.of(imovelResponse));
		when(locacaoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(locacao));
//		when(locacaoRepository.encerraContratoLocacao(Mockito.anyInt())).thenReturn(locacao);
		when(locacaoRepository.save(Mockito.any())).thenReturn(locacaoEntrada);
		
		doReturn(formatter.parse("2023-05-08")).when(service).retornaDataDeHoje();
		
		//acao
		service.realizaRenovacaoContrato(mapper.map(locacao, LocacaoDto.class));
		
		//validacao
		verify(usuarioServiceImpl, times(2)).findUserById(Mockito.anyInt());
		verify(imovelServiceImpl, times(2)).findById(Mockito.anyInt());
		verify(locacaoRepository, times(1)).findById(Mockito.anyInt());
//		verify(locacaoRepository, times(1)).encerraContratoLocacao(Mockito.anyInt());
	}
	
	@Test
	public void naoDeveRealizarARenovacaoDeContratoEmVigencia() throws ParseException {
		// cenario
		Usuario usuario = UsuarioBuilder.umUsuario().comId(1).agora();
		Inquilino inquilino = InquilinoBuilder.umInquilino().comId(1).agora();
		
		Imovel imovel = ImovelBuilder.umImovel().comId(1).agora();
		ImovelResponseDto imovelResponse = mapper.map(imovel, ImovelResponseDto.class);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Locacao locacao = LocacaoBuilder.umaLocacao().comId(1).comDataTerminoContrato(formatter.parse("2023-05-07")).comInquilino(inquilino).comImovel(imovel).agora();
		
		when(usuarioServiceImpl.findUserById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
		when(imovelServiceImpl.findById(Mockito.anyInt())).thenReturn(Optional.of(imovelResponse));
		when(locacaoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(locacao));
		
		exception.expect(ValidationException.class);
		exception.expectMessage("Não e possivel renovar um contrato que está em vigor");
		
		//acao
		service.realizaRenovacaoContrato(mapper.map(locacao, LocacaoDto.class));
		
		//validacao
		verify(usuarioServiceImpl, times(2)).findUserById(Mockito.anyInt());
		verify(imovelServiceImpl, times(2)).findById(Mockito.anyInt());
		verify(locacaoRepository, times(1)).findById(Mockito.anyInt());
	}
	
	@Test
	public void naoDeveRealizarARenovacaoDeContratoInexistente() throws ParseException {
		// cenario
		Usuario usuario = UsuarioBuilder.umUsuario().comId(1).agora();
		Inquilino inquilino = InquilinoBuilder.umInquilino().comId(1).agora();
		
		Imovel imovel = ImovelBuilder.umImovel().comId(1).agora();
		ImovelResponseDto imovelResponse = mapper.map(imovel, ImovelResponseDto.class);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Locacao locacao = LocacaoBuilder.umaLocacao().comId(1).comDataTerminoContrato(formatter.parse("2023-05-07")).comInquilino(inquilino).comImovel(imovel).agora();
		
		when(usuarioServiceImpl.findUserById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
		when(imovelServiceImpl.findById(Mockito.anyInt())).thenReturn(Optional.of(imovelResponse));
		when(locacaoRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		
		exception.expect(ObjectNotFoundException.class);
		exception.expectMessage("Locacao não encontrada");
		
		//acao
		service.realizaRenovacaoContrato(mapper.map(locacao, LocacaoDto.class));
		
		//validacao
		verify(usuarioServiceImpl, times(2)).findUserById(Mockito.anyInt());
		verify(imovelServiceImpl, times(2)).findById(Mockito.anyInt());
		verify(locacaoRepository, times(1)).findById(Mockito.anyInt());
	}
}
