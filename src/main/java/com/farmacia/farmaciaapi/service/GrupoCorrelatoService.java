package com.farmacia.farmaciaapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.model.GrupoCorrelato;
import com.farmacia.farmaciaapi.repository.GrupoCorrelatoRepository;

@Service
public class GrupoCorrelatoService {
	@Autowired
	private GrupoCorrelatoRepository grupoCorrelatoRepository;

	public GrupoCorrelato atualizar(Long codigo, GrupoCorrelato grupo) {

		GrupoCorrelato grupoSalvo = this.grupoCorrelatoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		BeanUtils.copyProperties(grupo, grupoSalvo, "codigo");

		return this.grupoCorrelatoRepository.save(grupoSalvo);
	}

}
