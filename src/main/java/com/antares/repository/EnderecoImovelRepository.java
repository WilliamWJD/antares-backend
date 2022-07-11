package com.antares.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.antares.domain.EnderecoImovel;

public interface EnderecoImovelRepository extends JpaRepository<EnderecoImovel, Integer>{
//	@Query(value = "select \r\n"
//			+ "* \r\n"
//			+ "from endereco_imovel ei \r\n"
//			+ "inner join imovel i ON i.endereco_id = ei.id \r\n"
//			+ "inner join usuario u on u.id = i.usuario_id \r\n"
//			+ "where ei.cep = :cep \r\n"
//			+ "and ei.numero = :numero \r\n"
//			+ "and i.id <> :id\r\n"
//			+ "and u.id = :userId", nativeQuery = true)
//	Optional<EnderecoImovel> findByCepAndNumero(@Param("cep") String cep,  @Param("numero")Integer numero,@Param("id")Integer id, @Param("userId") Integer userId);

	@Query(value = "select * from endereco_imovel ei \r\n"
			+ "inner join imovel i on ei.id = i.endereco_id\r\n"
			+ "inner join usuario u on i.usuario_id = u.id\r\n"
			+ "where ei.cep = :cep and ei.numero = :numero\r\n"
			+ "and i.usuario_id  = :userId", nativeQuery = true)
	EnderecoImovel buscarEnderecoImovelPorNumeroCepImovelId(@Param("cep") String cep, @Param("numero") Integer numero, @Param("userId") Integer userId);
}
