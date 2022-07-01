package com.antares.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antares.domain.Inquilino;

@Repository
public interface InquilinoRepository extends JpaRepository<Inquilino, Integer> {
	public Page<Inquilino> findByUsuarioId(Integer usuarioId, Pageable pageRequest);
	public Optional<Inquilino> findByIdAndUsuarioId(Integer id, Integer usuarioId);
	public void deleteByIdAndUsuarioId(Integer id, Integer usuarioId);
}
