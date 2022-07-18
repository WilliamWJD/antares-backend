package com.antares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.antares.domain.EnderecoImovel;

public interface EnderecoImovelRepository extends JpaRepository<EnderecoImovel, Integer>{
	@Query(value = "select * from endereco_imovel ei \r\n"
			+ "inner join imovel i on ei.id = i.endereco_id\r\n"
			+ "inner join usuario u on i.usuario_id = u.id\r\n"
			+ "where ei.cep = :cep and ei.numero = :numero\r\n"
			+ "and i.usuario_id  = :userId", nativeQuery = true)
	EnderecoImovel buscarEnderecoImovelPorNumeroCepImovelId(@Param("cep") String cep, @Param("numero") Integer numero, @Param("userId") Integer userId);
}
