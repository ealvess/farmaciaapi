package com.farmacia.farmaciaapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.model.CategoriaCorrelato;
import com.farmacia.farmaciaapi.repository.CategoriaCorrelatoRepository;

@Service
public class GrupoCorrelatoService {
	@Autowired
	private CategoriaCorrelatoRepository categoriaCorrelatoRepository;

	public CategoriaCorrelato atualizar(Long codigo, CategoriaCorrelato grupo) {

		CategoriaCorrelato grupoSalvo = this.categoriaCorrelatoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		BeanUtils.copyProperties(grupo, grupoSalvo, "codigo");

		return this.categoriaCorrelatoRepository.save(grupoSalvo);
	}

}
