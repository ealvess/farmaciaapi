package com.farmacia.farmaciaapi.repository.saidacorrelato;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.dto.EstatisticaSaidaCorrelatoPorMes;
import com.farmacia.farmaciaapi.model.ItemSaidaCorrelato;
import com.farmacia.farmaciaapi.repository.filter.ItemSaidaCorrelatoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoSaidaDeCorrelatos;

public interface ItemSaidaCorrelatoRepositoryQuery {
	
	public List<EstatisticaSaidaCorrelatoPorMes> porMes(LocalDate mesReferencia);

	public Page<ItemSaidaCorrelato> filtrar(ItemSaidaCorrelatoFilter itemSaidaCorrelatoFilter, Pageable pageable);
	public Page<ResumoSaidaDeCorrelatos> resumo(ItemSaidaCorrelatoFilter itemSaidaCorrelatoFilter, Pageable pageable);

}
