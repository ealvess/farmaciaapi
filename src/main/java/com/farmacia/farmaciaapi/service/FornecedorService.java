package com.farmacia.farmaciaapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.model.Fornecedor;
import com.farmacia.farmaciaapi.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	public Fornecedor atualizar(Long codigo, Fornecedor fornecedor) {

		Fornecedor fornecedorSalvo = this.fornecedorRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		BeanUtils.copyProperties(fornecedor, fornecedorSalvo, "codigo");

		return this.fornecedorRepository.save(fornecedorSalvo);
	}
}
