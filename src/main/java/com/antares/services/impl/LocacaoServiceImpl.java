package com.antares.services.impl;

import java.math.BigDecimal;
import java.util.Calendar;
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
import com.antares.services.exceptions.ValidationException;

@Service
public class LocacaoServiceImpl implements LocacaoService {

	@Autowired
	private LocacaoRepository locacaoRepository;

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private LocacaoMapper locacaoMapper;

	@Override
	public LocacaoDto salvarLocacao(LocacaoDto locacaoDto, Integer userId) {
		Optional<Usuario> usuario = usuarioServiceImpl.findUserById(userId);
		
		if(locacaoDto.getTempoContrato() == null)
			locacaoDto.setTempoContrato(12);
		
		if(locacaoDto.getValorCaucao() == null)
			locacaoDto.setValorCaucao(new BigDecimal(0));
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(locacaoDto.getDataInicio());
		calendar.add(Calendar.MONTH, locacaoDto.getTempoContrato());
		locacaoDto.setDataFim(calendar.getTime());

		Locacao locacao = locacaoMapper.mapearDtoParaEntity(locacaoDto,
				mapper.map(locacaoDto.getInquilino(), Inquilino.class),
				mapper.map(locacaoDto.getImovel(), Imovel.class));

		if(usuario.isPresent()) {
			locacao.setUsuario(usuario.get());
		}else {
			throw new ValidationException("Usuario não encontrado");
		}

		return mapper.map(locacaoRepository.save(locacao), LocacaoDto.class);
	}
}
