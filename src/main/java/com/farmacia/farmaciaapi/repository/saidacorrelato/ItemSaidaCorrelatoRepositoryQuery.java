package com.farmacia.farmaciaapi.repository.saidacorrelato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.model.ItemSaidaCorrelato;
import com.farmacia.farmaciaapi.repository.filter.ItemSaidaCorrelatoFilter;

public interface ItemSaidaCorrelatoRepositoryQuery {

	public Page<ItemSaidaCorrelato> filtrar(ItemSaidaCorrelatoFilter itemSaidaCorrelatoFilter, Pageable pageable);
}
