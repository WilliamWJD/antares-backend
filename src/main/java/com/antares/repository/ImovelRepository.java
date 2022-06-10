package com.antares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antares.domain.Imovel;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Integer>{
	
}