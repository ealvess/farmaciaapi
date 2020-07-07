package com.farmacia.farmaciaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.farmaciaapi.model.Medico;
import com.farmacia.farmaciaapi.repository.medico.MedicoRepositoryQuery;

public interface MedicoRepository extends JpaRepository<Medico, Long>, MedicoRepositoryQuery {

}
