package com.antares.services.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.antares.builders.EnderecoBuilder;
import com.antares.builders.UsuarioBuilder;
import com.antares.domain.EnderecoUsuario;
import com.antares.domain.Usuario;
import com.antares.dto.endereco.EnderecoUsuarioDTO;
import com.antares.dto.usuario.UsuarioDTO;
import com.antares.repository.UsuarioRepository;
import com.antares.services.EnderecoUsuarioService;
import com.antares.services.exceptions.ObjectNotFoundException;

@RunWith(SpringRunner.class)
public class UsuarioServiceImplTest {

	@Spy
	private ModelMapper mapper;

	@Mock
	private UsuarioRepository usuarioRepository;

	@Mock
	private EnderecoUsuarioService enderecoService;

	@InjectMocks
	private UsuarioServiceImpl service;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void salvaUsuarioComSucesso() throws ParseException {
		// cenario
		EnderecoUsuario endereco = EnderecoBuilder.umEnderecoUsuario().agora();
		Usuario usuario = UsuarioBuilder.umUsuario().comEnderecos(Arrays.asList(endereco)).agora();

		List<EnderecoUsuarioDTO> enderecosUsuarioDtoResponse = new ArrayList<>();
		enderecosUsuarioDtoResponse
				.add(mapper.map(EnderecoBuilder.umEnderecoUsuario().comId(1).agora(), EnderecoUsuarioDTO.class));

		Usuario usuarioResponse = UsuarioBuilder.umUsuario().comId(1).comEnderecos(enderecosUsuarioDtoResponse.stream()
				.map(item -> mapper.map(item, EnderecoUsuario.class)).collect(Collectors.toList())).agora();

		when(usuarioRepository.save(Mockito.any())).thenReturn(usuarioResponse);
		when(enderecoService.salvar(Mockito.anyList(), Mockito.any())).thenReturn(enderecosUsuarioDtoResponse);

		// acao
		service.save(mapper.map(usuario, UsuarioDTO.class));

		// validacao
		verify(usuarioRepository).save(Mockito.any());
		verify(enderecoService).salvar(Mockito.anyList(), Mockito.any());
	}
	
	@Test
	public void buscaUsuarioPorId() throws ParseException {
		//cenario
		Usuario usuario = UsuarioBuilder.umUsuario().comId(1).agora();
		
		when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
		
		//acao
		service.findUserById(1);
		
		//verificacao
		verify(usuarioRepository).findById(Mockito.anyInt());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void lancaExceptionAoBuscaUsuarioPorId() throws ParseException {
		//cenario
		when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		
		//acao
		service.findUserById(1);
		
		//verificacao
		verify(usuarioRepository).findById(Mockito.anyInt());
	}
}
