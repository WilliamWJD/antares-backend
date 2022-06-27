package com.antares.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.antares.domain.Imovel;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Integer>{
	public Optional<Imovel>findByIdAndUsuarioId(Integer id, Integer userId);
	public List<Imovel> findAllByUsuarioId(Integer userId);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Imovel AS i WHERE i.id =:id and i.usuario.id = :userId")
	public void deleteImovelByIdAndUsuarioId(@Param("id") Integer id, @Param("userId") Integer userId);
}