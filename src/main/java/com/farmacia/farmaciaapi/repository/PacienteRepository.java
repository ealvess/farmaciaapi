package com.farmacia.farmaciaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.farmaciaapi.model.Paciente;
import com.farmacia.farmaciaapi.repository.paciente.PacienteRepositoryQuery;

public interface PacienteRepository extends JpaRepository<Paciente, Long>, PacienteRepositoryQuery{

}
