package com.farmacia.farmaciaapi.repository.medicamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.Medicamento;
import com.farmacia.farmaciaapi.repository.filter.MedicamentoFilter;

public interface MedicamentoRepositoryQuery {
	
	public Page<Medicamento> filtrar(MedicamentoFilter medicamentoFilter, Pageable pageable);
}
