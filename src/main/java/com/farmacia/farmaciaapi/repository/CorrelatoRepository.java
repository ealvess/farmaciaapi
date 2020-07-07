package com.farmacia.farmaciaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.farmaciaapi.model.Correlato;
import com.farmacia.farmaciaapi.repository.correlato.CorrelatoRepositoryQuery;

public interface CorrelatoRepository extends JpaRepository<Correlato, Long>, CorrelatoRepositoryQuery {

}
