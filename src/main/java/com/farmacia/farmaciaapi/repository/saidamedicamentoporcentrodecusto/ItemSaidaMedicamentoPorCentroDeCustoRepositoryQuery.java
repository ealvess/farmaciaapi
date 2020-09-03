package com.farmacia.farmaciaapi.repository.saidamedicamentoporcentrodecusto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmacia.farmaciaapi.dto.EstatisticaSaidaMedicamentoPorCentroDeCusto;
import com.farmacia.farmaciaapi.model.ItemSaidaMedicamentoPorCentroDeCusto;
import com.farmacia.farmaciaapi.repository.filter.ItemSaidaMedicamentoPorCentroDeCustoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoSaidaDeMedicamentosPorCentroDeCusto;

public interface ItemSaidaMedicamentoPorCentroDeCustoRepositoryQuery {
	
	public List<EstatisticaSaidaMedicamentoPorCentroDeCusto> porMes(LocalDate mesReferencia);

	
	public Page<ItemSaidaMedicamentoPorCentroDeCusto> filtrar(ItemSaidaMedicamentoPorCentroDeCustoFilter itemSaidaMedicamentoFilter, Pageable pageable);
	public Page<ResumoSaidaDeMedicamentosPorCentroDeCusto> resumo(ItemSaidaMedicamentoPorCentroDeCustoFilter itemSaidaMedicamentoFilter, Pageable pageable);
}
