package com.antares.dto.locacao;

import java.math.BigDecimal;
import java.util.Date;

import com.antares.dto.imovel.ImovelDto;
import com.antares.dto.inquilino.InquilinoDTO;
import com.antares.dto.usuario.UsuarioDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class LocacaoDto {
	private Integer id;
	private Date dataInicio;
	private Date dataFim;
	private Integer tempoContrato;
	private Integer diaPagamentoAluguel;
	private BigDecimal valorCaucao;
	private Boolean status;

	private UsuarioDTO usuario;
	private InquilinoDTO inquilino;
	private ImovelDto imovel;
}
