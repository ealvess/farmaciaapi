package com.farmacia.farmaciaapi.repository.medicamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.Medicamento;
import com.farmacia.farmaciaapi.repository.filter.MedicamentoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoMedicamento;

public interface MedicamentoRepositoryQuery {
	
	public Page<Medicamento> filtrar(MedicamentoFilter medicamentoFilter, Pageable pageable);
	public Page<ResumoMedicamento> resumo(MedicamentoFilter medicamentoFilter, Pageable pageable);
}
