package com.farmacia.farmaciaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.farmaciaapi.model.EntradaCorrelato;
import com.farmacia.farmaciaapi.repository.entradacorrelato.EntradaCorrelatoRepositoryQuery;

public interface EntradaCorrelatoRepository extends JpaRepository<EntradaCorrelato, Long>, EntradaCorrelatoRepositoryQuery {

}
