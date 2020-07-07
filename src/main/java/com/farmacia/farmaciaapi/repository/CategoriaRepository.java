package com.farmacia.farmaciaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.farmaciaapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
