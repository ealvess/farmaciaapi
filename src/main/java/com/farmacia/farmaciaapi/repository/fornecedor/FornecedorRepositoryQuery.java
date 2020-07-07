package com.farmacia.farmaciaapi.repository.fornecedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.Fornecedor;
import com.farmacia.farmaciaapi.repository.filter.FornecedorFilter;

public interface FornecedorRepositoryQuery {

	public Page<Fornecedor> filtrar(FornecedorFilter fornecedorFilter, Pageable pageable);
}
