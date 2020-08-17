package com.farmacia.farmaciaapi.repository.saidamedicamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.ItemSaidaMedicamento;
import com.farmacia.farmaciaapi.repository.filter.ItemSaidaMedicamentoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoSaidaDeMedicamentos;

public interface ItemSaidaMedicamentoRepositoryQuery {
	
	public Page<ItemSaidaMedicamento> filtrar(ItemSaidaMedicamentoFilter itemSaidaMedicamentoFilter, Pageable pageable);
	public Page<ResumoSaidaDeMedicamentos> resumo(ItemSaidaMedicamentoFilter itemSaidaMedicamentoFilter, Pageable pageable);
}
