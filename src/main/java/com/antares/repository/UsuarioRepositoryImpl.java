package com.antares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antares.domain.Usuario;

@Repository
public interface UsuarioRepositoryImpl extends JpaRepository<Usuario, Integer> {
}
