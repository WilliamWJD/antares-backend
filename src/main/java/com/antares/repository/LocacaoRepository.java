package com.antares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antares.domain.Locacao;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Integer>{
//	Locacao verificaSeImovelLocado(Integer idUsuario, Integer idLocacao, Integer idImovel);
}
