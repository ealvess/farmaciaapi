package com.farmacia.farmaciaapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.model.Categoria;
import com.farmacia.farmaciaapi.model.Medicamento;
import com.farmacia.farmaciaapi.repository.CategoriaRepository;
import com.farmacia.farmaciaapi.repository.MedicamentoRepository;
import com.farmacia.farmaciaapi.service.exception.ObjetoInexistenteOuInativoException;

@Service
public class MedicamentoService {

	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Medicamento atualizar(Long codigo, Medicamento medicamento) {

		Medicamento medicamentoSalvo = buscarMedicamentoSalvo(codigo);

		BeanUtils.copyProperties(medicamento, medicamentoSalvo, "codigo");

		return this.medicamentoRepository.save(medicamentoSalvo);
	}
	
	public Medicamento salvar(Medicamento medicamento) {
		Optional<Categoria> categoriaOpt = categoriaRepository.findById(medicamento.getCategoria().getCodigo());

	    if (!categoriaOpt.isPresent() || categoriaOpt.get().isInativo()) {
	        throw new ObjetoInexistenteOuInativoException();
	    }
	    
	    return medicamentoRepository.save(medicamento);
	}
	
	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Medicamento medicamentoSalvo = buscarMedicamentoSalvo(codigo);
		medicamentoSalvo.setAtivo(ativo);
		medicamentoRepository.save(medicamentoSalvo);
		
	}

	public Medicamento buscarMedicamentoSalvo(Long codigo) {
		Medicamento medicamentoSalvo = this.medicamentoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return medicamentoSalvo;
	}

	


}
