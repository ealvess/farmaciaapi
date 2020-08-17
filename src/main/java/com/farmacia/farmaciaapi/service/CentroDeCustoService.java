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

		CentroDeCusto centroDeCustoSalvo = buscarCentroDeCusto(codigo);

		BeanUtils.copyProperties(centroDeCusto, centroDeCustoSalvo, "codigo");

		return this.centroDeCustoRepository.save(centroDeCustoSalvo);
	}
	
	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		CentroDeCusto centroDeCustoSalvo = buscarCentroDeCusto(codigo);
		centroDeCustoSalvo.setAtivo(ativo);
		centroDeCustoRepository.save(centroDeCustoSalvo);
		
	}

	public CentroDeCusto buscarCentroDeCusto(Long codigo) {
		CentroDeCusto centroDeCustoSalvo = this.centroDeCustoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return centroDeCustoSalvo;
	}

}
