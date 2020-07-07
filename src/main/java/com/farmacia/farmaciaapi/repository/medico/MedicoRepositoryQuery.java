package com.farmacia.farmaciaapi.repository.medico;

import java.util.List;

import com.farmacia.farmaciaapi.model.Medico;
import com.farmacia.farmaciaapi.repository.filter.MedicoFilter;

public interface MedicoRepositoryQuery {
	
	public List<Medico> filtrar(MedicoFilter medicoFilter);
}
