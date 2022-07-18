package com.antares.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antares.domain.Imovel;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Integer>{
	public Optional<Imovel>findByIdAndUsuarioId(Integer id, Integer userId);
	public List<Imovel> findAllByUsuarioId(Integer userId);
	void deleteByIdAndUsuarioId(Integer id, Integer userId);
}