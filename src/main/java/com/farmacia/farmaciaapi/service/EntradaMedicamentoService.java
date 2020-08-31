package com.farmacia.farmaciaapi.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.model.EntradaMedicamento;
import com.farmacia.farmaciaapi.model.Fornecedor;
import com.farmacia.farmaciaapi.model.Medicamento;
import com.farmacia.farmaciaapi.repository.EntradaMedicamentoRepository;
import com.farmacia.farmaciaapi.repository.FornecedorRepository;
import com.farmacia.farmaciaapi.repository.MedicamentoRepository;
import com.farmacia.farmaciaapi.service.exception.ObjetoInexistenteOuInativoException;

@Service
public class EntradaMedicamentoService {

	@Autowired
	private EntradaMedicamentoRepository entradaMedicamentoRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;

	private BigDecimal qtdNova;

	
	public EntradaMedicamento atualizar(Long codigo, EntradaMedicamento entradaMedicamento) {

		EntradaMedicamento medicamentoSalvo = buscarMedicamentoPeloCodigo(codigo);

		BeanUtils.copyProperties(entradaMedicamento, medicamentoSalvo, "codigo");

		return this.entradaMedicamentoRepository.save(medicamentoSalvo);
	}

	public void atualizarQuantidade(Long codigo, BigDecimal quantidade) {
		EntradaMedicamento medicamentoSalvo = buscarMedicamentoPeloCodigo(codigo);
		qtdNova = medicamentoSalvo.getQuantidade().subtract(quantidade);
		medicamentoSalvo.setQuantidade(qtdNova);
		entradaMedicamentoRepository.save(medicamentoSalvo);
	}
	

	public EntradaMedicamento salvar(EntradaMedicamento entradaMedicamento) {
		Optional<Medicamento> medicamentoOpt = medicamentoRepository.findById(entradaMedicamento.getMedicamento().getCodigo());
		Optional<Fornecedor> forencedorOpt = fornecedorRepository.findById(entradaMedicamento.getFornecedor().getCodigo());

	    if (!medicamentoOpt.isPresent() || medicamentoOpt.get().isInativo() || !forencedorOpt.isPresent() || forencedorOpt.get().isInativo()) {
	        throw new ObjetoInexistenteOuInativoException();
	    }
	    
	    return entradaMedicamentoRepository.save(entradaMedicamento);
	}

	private EntradaMedicamento buscarMedicamentoPeloCodigo(Long codigo) {
		EntradaMedicamento medicamentoSalvo = this.entradaMedicamentoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return medicamentoSalvo;
	}


}
