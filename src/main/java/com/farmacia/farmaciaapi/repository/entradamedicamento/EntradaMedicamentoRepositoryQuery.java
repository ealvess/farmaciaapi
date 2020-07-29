package com.farmacia.farmaciaapi.repository.entradamedicamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.EntradaMedicamento;
import com.farmacia.farmaciaapi.repository.filter.EntradaMedicamentoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoEntradaMedicamento;

public interface EntradaMedicamentoRepositoryQuery {
	
	public Page<EntradaMedicamento> filtrar(EntradaMedicamentoFilter entradaMedicamentoFilter, Pageable pageable);
	public Page<ResumoEntradaMedicamento> resumo(EntradaMedicamentoFilter entradaMedicamentoFilter, Pageable pageable);
}
