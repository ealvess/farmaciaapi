package com.farmacia.farmaciaapi.repository.entradacorrelato;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.dto.EstatisticaEntradaCorrelatoPorMes;
import com.farmacia.farmaciaapi.model.EntradaCorrelato;
import com.farmacia.farmaciaapi.repository.filter.EntradaCorrelatoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoEntradaCorrelatos;

public interface EntradaCorrelatoRepositoryQuery {
	
	public List<EstatisticaEntradaCorrelatoPorMes> porCorrelato(LocalDate mesReferencia);
	public List<EstatisticaEntradaCorrelatoPorMes> porMes(LocalDate inicio, LocalDate fim);

	
	public Page<EntradaCorrelato> filtrar(EntradaCorrelatoFilter entradaCorrelatoFilter, Pageable pageable);
	public Page<ResumoEntradaCorrelatos> resumo(EntradaCorrelatoFilter entradaCorrelatoFilter, Pageable pageable);
}
