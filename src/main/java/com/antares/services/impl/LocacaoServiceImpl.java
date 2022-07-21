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
import com.antares.dto.locacao.LocacaoDto;
import com.antares.mapper.LocacaoMapper;
import com.antares.repository.LocacaoRepository;
import com.antares.services.LocacaoService;
import com.antares.services.exceptions.ObjectNotFoundException;
import com.antares.services.exceptions.ValidationException;

@Service
public class LocacaoServiceImpl implements LocacaoService {

	@Autowired
	private LocacaoRepository locacaoRepository;

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@Autowired
	private ImovelServiceImpl imovelServiceImpl;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private LocacaoMapper locacaoMapper;

	@Override
	public LocacaoDto salvarLocacao(LocacaoDto locacaoDto, Integer userId) {
		Optional<Usuario> usuario = usuarioServiceImpl.findUserById(userId);

		// verifica se o imovel da locação existe
		imovelServiceImpl.findById(locacaoDto.getImovel().getId(), userId);

		// verifica se o imovel passado está com uma locação ativa
		Locacao verificaImovelLocado = locacaoRepository.verificaSeImovelLocado(userId, locacaoDto.getImovel().getId());

		if (verificaImovelLocado != null) {
			throw new ValidationException("Esse imovel está com uma locacão ativa.");
		}

		// seta tempo de contrato e valor de caução padrão
		if (locacaoDto.getTempoContrato() == null)
			locacaoDto.setTempoContrato(12);

		if (locacaoDto.getValorCaucao() == null)
			locacaoDto.setValorCaucao(new BigDecimal(0));

		// realiza calculo de data de término do contrato
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(locacaoDto.getDataInicio());
		calendar.add(Calendar.MONTH, locacaoDto.getTempoContrato());
		locacaoDto.setDataFim(calendar.getTime());

		Locacao locacao = locacaoMapper.mapearDtoParaEntity(locacaoDto,
				mapper.map(locacaoDto.getInquilino(), Inquilino.class),
				mapper.map(locacaoDto.getImovel(), Imovel.class));

		// verifica se o usuario da requisição realmente existe
		if (usuario.isPresent()) {
			locacao.setUsuario(usuario.get());
		} else {
			throw new ValidationException("Usuario não encontrado");
		}

		return mapper.map(locacaoRepository.save(locacao), LocacaoDto.class);
	}

	@Override
	public LocacaoDto realizaRenovacaoContrato(LocacaoDto locacaoDto, Integer userId) {
		usuarioServiceImpl.findUserById(userId);

		// verifica se o imovel da locação existe
		imovelServiceImpl.findById(locacaoDto.getImovel().getId(), userId);
		
		// retorna a locacao
		Optional<Locacao> locacao = locacaoRepository.findById(locacaoDto.getId());
		
		if(!locacao.isPresent()) {
			throw new ObjectNotFoundException("Locacao não encontrada");
		}
		
		// verifica se a data de termino do contrato e menor que a data atual
		if(locacaoDto.getDataFim().compareTo(retornaDataDeHoje()) > 0) {
			throw new ValidationException("Não e possivel renovar um contrato que está em vigor");
		}
		
		// encerra o contrato antigo
//		locacaoRepository.encerraContratoLocacao(locacaoDto.getId());
		
		// cria um novo contrato de locacao
		LocacaoDto novaLocacao = mapper.map(locacao, LocacaoDto.class);
		novaLocacao.setId(null);
		
		salvarLocacao(locacaoDto, userId);
		
		return null;
	}

	protected Date retornaDataDeHoje() {
		return new Date();
	}
}
