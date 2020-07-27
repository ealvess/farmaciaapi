package com.farmacia.farmaciaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.farmaciaapi.model.Categoria;
import com.farmacia.farmaciaapi.repository.categoria.CategoriaRepositoryQuery;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>, CategoriaRepositoryQuery {

}
