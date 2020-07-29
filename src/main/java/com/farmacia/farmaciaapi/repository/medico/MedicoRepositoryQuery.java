package com.farmacia.farmaciaapi.repository.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.Medico;
import com.farmacia.farmaciaapi.repository.filter.MedicoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoMedicos;

public interface MedicoRepositoryQuery {
	
	public Page<Medico> filtrar(MedicoFilter medicoFilter, Pageable pageable);
	public Page<ResumoMedicos> resumo(MedicoFilter medicoFilter, Pageable pageable);
}
