package com.antares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.antares.domain.Locacao;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Integer> {

	@Query(value = "select*\r\n" + "from locacao\r\n" + "inner join imovel on locacao.imovel_id  = imovel.id\r\n"
			+ "inner join usuario on locacao.usuario_id = usuario.id\r\n" + "where imovel.id = :idImovel\r\n"
			+ "and usuario.id = :idUsuario\r\n" + "and locacao.data_fim >= current_date\r\n"
			+ "and locacao.status = true;", nativeQuery = true)
	Locacao verificaSeImovelLocado(@Param("idImovel") Integer idImovel, @Param("idUsuario") Integer idUsuario);
	
//	@Query(value = "", nativeQuery = true)
//	void encerraContratoLocacao(Integer idLocacao);
}
