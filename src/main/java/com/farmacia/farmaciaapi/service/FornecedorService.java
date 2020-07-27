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

		Fornecedor fornecedorSalvo = buscarFornecedorPorCodigo(codigo);

		BeanUtils.copyProperties(fornecedor, fornecedorSalvo, "codigo");

		return this.fornecedorRepository.save(fornecedorSalvo);
	}

	public Fornecedor buscarFornecedorPorCodigo(Long codigo) {
		Fornecedor fornecedorSalvo = this.fornecedorRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return fornecedorSalvo;
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Fornecedor fornecedorSalvo = buscarFornecedorPorCodigo(codigo);
		fornecedorSalvo.setAtivo(ativo);
		fornecedorRepository.save(fornecedorSalvo);		
	}
}
