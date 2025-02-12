package com.farmacia.farmaciaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.farmaciaapi.model.ItemSaidaMedicamentoPorCentroDeCusto;
import com.farmacia.farmaciaapi.repository.saidamedicamentoporcentrodecusto.ItemSaidaMedicamentoPorCentroDeCustoRepositoryQuery;

public interface ItemSaidaMedicamentoPorCentroDeCustoRepository extends JpaRepository<ItemSaidaMedicamentoPorCentroDeCusto, Long>, ItemSaidaMedicamentoPorCentroDeCustoRepositoryQuery {

}
