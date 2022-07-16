package com.antares.services.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
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
}
