package com.farmacia.farmaciaapi.repository.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.Categoria;
import com.farmacia.farmaciaapi.repository.filter.CategoriaFilter;

public interface CategoriaRepositoryQuery {
	
	public Page<Categoria> filtrar(CategoriaFilter categoriaFilter, Pageable pageable);
}
