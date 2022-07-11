package com.antares.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

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

	Optional<EnderecoImovel> findByCepAndNumero(String cep, Integer numero);
}
