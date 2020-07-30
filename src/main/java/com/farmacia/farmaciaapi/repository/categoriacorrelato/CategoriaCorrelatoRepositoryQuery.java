package com.farmacia.farmaciaapi.repository.categoriacorrelato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.CategoriaCorrelato;
import com.farmacia.farmaciaapi.repository.filter.CategoriaCorrelatoFilter;

public interface CategoriaCorrelatoRepositoryQuery {
	
	public Page<CategoriaCorrelato> filtrar(CategoriaCorrelatoFilter categoriaCorrelatoFilter, Pageable pageable);
}
