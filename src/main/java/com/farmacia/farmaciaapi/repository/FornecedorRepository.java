package com.farmacia.farmaciaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.farmaciaapi.model.Fornecedor;
import com.farmacia.farmaciaapi.repository.fornecedor.FornecedorRepositoryQuery;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>, FornecedorRepositoryQuery{

}
