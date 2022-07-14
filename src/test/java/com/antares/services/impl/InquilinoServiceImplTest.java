package com.antares.services.impl;

import static org.mockito.Mockito.when;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.antares.domain.Inquilino;
import com.antares.domain.Usuario;
import com.antares.dto.inquilino.InquilinoCadastroDto;
import com.antares.repository.InquilinoRepository;
import com.antares.repository.UsuarioRepository;
import com.antares.services.builders.InquilinoBuilder;
import com.antares.services.builders.UsuarioBuilder;

@RunWith(MockitoJUnitRunner.class)
public class InquilinoServiceImplTest {
	
	@Mock
	private InquilinoRepository repository;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@InjectMocks
	private InquilinoServiceImpl service;
	
	@Mock
	private UsuarioServiceImpl usuarioService;

	@Mock
	private ModelMapper modelMapper;
	
	@Test
	public void deveSalvarInquilinoComSucesso() throws ParseException {
		//cenario
		Usuario usuario = UsuarioBuilder.umUsuario().comId(1).agora();
		Inquilino inquilino = InquilinoBuilder.umInquilino().comUsuario(usuario).agora();
		Inquilino inquilinoResponse = InquilinoBuilder.umInquilino().comId(1).comUsuario(usuario).agora();
		
		when(repository.save(Mockito.any())).thenReturn(inquilinoResponse);
		ModelMapper mapper = new ModelMapper();
		
		//acao
		service.save(mapper.map(inquilino, InquilinoCadastroDto.class), Mockito.anyInt());
	}
}
