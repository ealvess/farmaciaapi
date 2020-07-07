package com.farmacia.farmaciaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.farmaciaapi.model.ItemSaidaMedicamento;
import com.farmacia.farmaciaapi.repository.saidamedicamento.ItemSaidaMedicamentoRepositoryQuery;

public interface ItemSaidaMedicamentoRepository extends JpaRepository<ItemSaidaMedicamento, Long>, ItemSaidaMedicamentoRepositoryQuery {

}
