package com.farmacia.farmaciaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.farmaciaapi.model.CategoriaCorrelato;
import com.farmacia.farmaciaapi.repository.categoriacorrelato.CategoriaCorrelatoRepositoryQuery;

public interface CategoriaCorrelatoRepository extends JpaRepository<CategoriaCorrelato, Long>, CategoriaCorrelatoRepositoryQuery {

}

