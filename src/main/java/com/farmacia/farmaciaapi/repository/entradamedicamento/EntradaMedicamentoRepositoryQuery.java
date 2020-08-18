package com.farmacia.farmaciaapi.repository.entradamedicamento;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.dto.EstatisticaEntradaMedicamento;
import com.farmacia.farmaciaapi.model.EntradaMedicamento;
import com.farmacia.farmaciaapi.repository.filter.EntradaMedicamentoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoEntradaMedicamento;

public interface EntradaMedicamentoRepositoryQuery {
	
	public List<EstatisticaEntradaMedicamento> porMedicamento(LocalDate mesReferencia);
	
	public Page<EntradaMedicamento> filtrar(EntradaMedicamentoFilter entradaMedicamentoFilter, Pageable pageable);
	public Page<ResumoEntradaMedicamento> resumo(EntradaMedicamentoFilter entradaMedicamentoFilter, Pageable pageable);
}
