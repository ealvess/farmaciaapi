package com.farmacia.farmaciaapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.model.CentroDeCusto;
import com.farmacia.farmaciaapi.repository.CentroDeCustoRepository;

@Service
public class CentroDeCustoService {

	@Autowired
	private CentroDeCustoRepository centroDeCustoRepository;

	public CentroDeCusto atualizar(Long codigo, CentroDeCusto centroDeCusto) {

		CentroDeCusto centroDeCustoSalvo = this.centroDeCustoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		BeanUtils.copyProperties(centroDeCusto, centroDeCustoSalvo, "codigo");

		return this.centroDeCustoRepository.save(centroDeCustoSalvo);
	}
}
