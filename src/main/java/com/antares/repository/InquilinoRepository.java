package com.antares.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.antares.domain.Inquilino;

@Repository
public interface InquilinoRepository extends JpaRepository<Inquilino, Integer> {
	public Page<Inquilino> findByUsuarioId(Integer usuarioId, Pageable pageRequest);

	public Optional<Inquilino> findByIdAndUsuarioId(Integer id, Integer usuarioId);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Inquilino AS i WHERE i.id =:id and i.usuario.id = :userId")
	public void deleteInquilinoByIdAndUsuarioId(@Param("id") Integer id, @Param("userId") Integer userId);
}
