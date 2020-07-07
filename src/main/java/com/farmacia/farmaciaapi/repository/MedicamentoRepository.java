package com.farmacia.farmaciaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.farmaciaapi.model.Medicamento;
import com.farmacia.farmaciaapi.repository.medicamento.MedicamentoRepositoryQuery;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>, MedicamentoRepositoryQuery {

}
