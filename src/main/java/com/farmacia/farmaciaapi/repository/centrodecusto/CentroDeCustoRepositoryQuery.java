package com.farmacia.farmaciaapi.repository.centrodecusto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.CentroDeCusto;
import com.farmacia.farmaciaapi.repository.filter.CentroDeCustoFilter;

public interface CentroDeCustoRepositoryQuery {
	
	public Page<CentroDeCusto> filtrar(CentroDeCustoFilter centroDeCustoFilter, Pageable pageable);
}
