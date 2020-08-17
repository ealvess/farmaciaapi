package com.farmacia.farmaciaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.farmaciaapi.model.CentroDeCusto;
import com.farmacia.farmaciaapi.repository.centrodecusto.CentroDeCustoRepositoryQuery;

public interface CentroDeCustoRepository extends JpaRepository<CentroDeCusto, Long>, CentroDeCustoRepositoryQuery{

}
