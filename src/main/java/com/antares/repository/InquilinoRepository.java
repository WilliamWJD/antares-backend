package com.antares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.antares.domain.Inquilino;

import java.util.List;

@Repository
public interface InquilinoRepository extends JpaRepository<Inquilino, Integer>{
	
	@Query(value = "select i from Inquilino i where i.usuario.id =:usuario_id")
    public List<Inquilino> findInquilinoByUser(@Param("usuario_id") Integer usuario_id);
}
