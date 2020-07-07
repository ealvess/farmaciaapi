package com.farmacia.farmaciaapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.model.Medicamento;
import com.farmacia.farmaciaapi.repository.MedicamentoRepository;

@Service
public class MedicamentoService {

	@Autowired
	private MedicamentoRepository medicamentoRepository;

	private Integer qtdNova;

	public Medicamento atualizar(Long codigo, Medicamento medicamento) {

		Medicamento medicamentoSalvo = buscarMedicamentoPeloCodigo(codigo);

		BeanUtils.copyProperties(medicamento, medicamentoSalvo, "codigo");

		return this.medicamentoRepository.save(medicamentoSalvo);
	}

	public void atualizarQuantidade(Long codigo, Integer quantidade) {
		Medicamento medicamentoSalvo = buscarMedicamentoPeloCodigo(codigo);
		qtdNova = medicamentoSalvo.getQuantidade() - quantidade;
		medicamentoSalvo.setQuantidade(qtdNova);
		medicamentoRepository.save(medicamentoSalvo);
	}

	private Medicamento buscarMedicamentoPeloCodigo(Long codigo) {
		Medicamento medicamentoSalvo = this.medicamentoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return medicamentoSalvo;
	}

}
