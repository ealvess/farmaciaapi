package com.farmacia.farmaciaapi.repository.saidacorrelato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.ItemSaidaCorrelato;
import com.farmacia.farmaciaapi.repository.filter.ItemSaidaCorrelatoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoSaidaDeCorrelatos;

public interface ItemSaidaCorrelatoRepositoryQuery {

	public Page<ItemSaidaCorrelato> filtrar(ItemSaidaCorrelatoFilter itemSaidaCorrelatoFilter, Pageable pageable);
	public Page<ResumoSaidaDeCorrelatos> resumo(ItemSaidaCorrelatoFilter itemSaidaCorrelatoFilter, Pageable pageable);

}
