package com.farmacia.farmaciaapi.repository.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.Paciente;
import com.farmacia.farmaciaapi.repository.filter.PacienteFilter;

public interface PacienteRepositoryQuery {
	
	public Page<Paciente> filtrar(PacienteFilter pacienteFilter, Pageable pageable);
}
