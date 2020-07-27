package com.farmacia.farmaciaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.farmaciaapi.model.EntradaMedicamento;
import com.farmacia.farmaciaapi.repository.entradamedicamento.EntradaMedicamentoRepositoryQuery;

public interface EntradaMedicamentoRepository extends JpaRepository<EntradaMedicamento, Long>, EntradaMedicamentoRepositoryQuery {

}
