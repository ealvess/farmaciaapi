package com.farmacia.farmaciaapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.model.Paciente;
import com.farmacia.farmaciaapi.repository.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository;

	public Paciente atualizar(Long codigo, Paciente paciente) {

		Paciente pacienteSalvo = this.pacienteRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		BeanUtils.copyProperties(paciente, pacienteSalvo, "codigo");

		return this.pacienteRepository.save(pacienteSalvo);
	}

}
