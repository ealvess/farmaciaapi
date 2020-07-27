package com.farmacia.farmaciaapi.repository.fornecedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.Fornecedor;
import com.farmacia.farmaciaapi.repository.filter.FornecedorFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoFornecedores;

public interface FornecedorRepositoryQuery {

	public Page<Fornecedor> filtrar(FornecedorFilter fornecedorFilter, Pageable pageable);
	public Page<ResumoFornecedores> resumo(FornecedorFilter fornecedorFilter, Pageable pageable);
}
