package com.antares.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antares.domain.Inquilino;

@Repository
public interface InquilinoRepository extends JpaRepository<Inquilino, Integer>{
	public Page<Inquilino> findByUsuarioId(Integer usuario_id, Pageable pageRequest);
}
