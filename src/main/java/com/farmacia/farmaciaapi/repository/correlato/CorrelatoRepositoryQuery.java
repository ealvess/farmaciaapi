package com.farmacia.farmaciaapi.repository.correlato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.Correlato;
import com.farmacia.farmaciaapi.repository.filter.CorrelatoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoCorrelatos;

public interface CorrelatoRepositoryQuery {
	
	public Page<Correlato> filtrar(CorrelatoFilter correlatoFilter, Pageable pageable);
	public Page<ResumoCorrelatos> resumo(CorrelatoFilter correlatoFilter, Pageable pageable);
}
