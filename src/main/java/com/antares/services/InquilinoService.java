package com.antares.services;

import java.util.List;

import com.antares.domain.Inquilino;

public interface InquilinoService {
	public Inquilino save(Inquilino inquilino);
	public List<Inquilino> findAllInquilinosByUsuario(Integer user_id);
}
