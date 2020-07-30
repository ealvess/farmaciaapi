package com.farmacia.farmaciaapi.repository.entradacorrelato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.EntradaCorrelato;
import com.farmacia.farmaciaapi.repository.filter.EntradaCorrelatoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoEntradaCorrelatos;

public interface EntradaCorrelatoRepositoryQuery {
	
	public Page<EntradaCorrelato> filtrar(EntradaCorrelatoFilter entradaCorrelatoFilter, Pageable pageable);
	public Page<ResumoEntradaCorrelatos> resumo(EntradaCorrelatoFilter entradaCorrelatoFilter, Pageable pageable);
}
