package com.farmacia.farmaciaapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.model.Medico;
import com.farmacia.farmaciaapi.repository.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;

	public Medico atualizar(Long codigo, Medico medico) {

		Medico medicoSalvo = this.medicoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		BeanUtils.copyProperties(medico, medicoSalvo, "codigo");

		return this.medicoRepository.save(medicoSalvo);
	}
}
