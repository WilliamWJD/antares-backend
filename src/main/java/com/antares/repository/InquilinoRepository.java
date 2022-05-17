package com.antares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antares.domain.Inquilino;

import java.util.List;

@Repository
public interface InquilinoRepository extends JpaRepository<Inquilino, Integer>{
    List<Inquilino> findAll();
}
