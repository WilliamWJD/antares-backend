package com.antares.services.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antares.domain.Imovel;
import com.antares.domain.Inquilino;
import com.antares.domain.Locacao;
import com.antares.domain.Usuario;
import com.antares.dto.ImovelResponseDto;
import com.antares.dto.InquilinoDTO;
import com.antares.dto.LocacaoDto;
import com.antares.dto.LocacaoDtoEntrada;
import com.antares.mapper.LocacaoMapper;
import com.antares.repository.LocacaoRepository;
import com.antares.security.UserSS;
import com.antares.services.InquilinoService;
import com.antares.services.LocacaoService;
import com.antares.services.UsuarioService;
import com.antares.services.exceptions.ObjectNotFoundException;
import com.antares.services.exceptions.ValidationException;

@Service
public class LocacaoServiceImpl implements LocacaoService {

	@Autowired
	private LocacaoRepository locacaoRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ImovelServiceImpl imovelServiceImpl;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private LocacaoMapper locacaoMapper;

	@Autowired
	private InquilinoService inquilinoService;

	@Override
	public LocacaoDto salvarLocacao(LocacaoDtoEntrada locacaoDtoEntrada) {
		// recupera dados de usuario
		UserSS user = usuarioService.authenticated();

		if (user == null) {
			throw new ValidationException("Acesso negado");
		}

		Optional<Usuario> usuario = usuarioService.findUserById(user.getId());

		// verifica se o imovel da locação existe
		Optional<ImovelResponseDto> imovel = imovelServiceImpl.findById(locacaoDtoEntrada.getImovelId());

		// verifica se o imovel passado está com uma locação ativa
		Locacao verificaImovelLocado = locacaoRepository.verificaSeImovelLocado(usuario.get().getId(),
				locacaoDtoEntrada.getImovelId());

		if (verificaImovelLocado != null) {
			throw new ValidationException("Esse imovel está com uma locacão ativa.");
		}

		// recupera dados de inquilino
		Optional<InquilinoDTO> inquilino = inquilinoService.buscar(locacaoDtoEntrada.getInquilinoId());

		// seta tempo de contrato e valor de caução padrão
		if (locacaoDtoEntrada.getTempoContrato() == null)
			locacaoDtoEntrada.setTempoContrato(12);

		if (locacaoDtoEntrada.getValorCaucao() == null)
			locacaoDtoEntrada.setValorCaucao(new BigDecimal(0));

		// realiza calculo de data de término do contrato
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(locacaoDtoEntrada.getDataInicio());
		calendar.add(Calendar.MONTH, locacaoDtoEntrada.getTempoContrato());
		locacaoDtoEntrada.setDataFim(calendar.getTime());

		Locacao locacao = locacaoMapper.mapearDtoParaEntity(locacaoDtoEntrada,
				mapper.map(inquilino.get(), Inquilino.class), mapper.map(imovel.get(), Imovel.class));

		locacao.setUsuario(usuario.get());

		return mapper.map(locacaoRepository.save(locacao), LocacaoDto.class);
	}

	@Override
	public LocacaoDto realizaRenovacaoContrato(LocacaoDto locacaoDto) {
//		UserSS user = usuarioService.authenticated();
//
//		if (user == null) {
//			throw new ValidationException("Acesso negado");
//		}
//
//		Optional<Usuario> usuario = usuarioService.findUserById(user.getId());
//
//		// verifica se o imovel da locação existe
//		imovelServiceImpl.findById(locacaoDto.getImovel().getId());
//
//		// retorna a locacao
//		Optional<Locacao> locacao = locacaoRepository.findById(locacaoDto.getId());
//
//		if (!locacao.isPresent()) {
//			throw new ObjectNotFoundException("Locacao não encontrada");
//		}
//
//		// verifica se a data de termino do contrato e menor que a data atual
//		if (locacaoDto.getDataFim().compareTo(retornaDataDeHoje()) > 0) {
//			throw new ValidationException("Não e possivel renovar um contrato que está em vigor");
//		}
//
//		// encerra o contrato antigo
////		locacaoRepository.encerraContratoLocacao(locacaoDto.getId());
//
//		// cria um novo contrato de locacao
//		LocacaoDto novaLocacao = mapper.map(locacao, LocacaoDto.class);
//		novaLocacao.setId(null);
//
//		return salvarLocacao(locacaoDto);
		return null;
	}

	protected Date retornaDataDeHoje() {
		return new Date();
	}
}
