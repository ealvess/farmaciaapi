package com.farmacia.farmaciaapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.model.CategoriaCorrelato;
import com.farmacia.farmaciaapi.model.Correlato;
import com.farmacia.farmaciaapi.repository.CategoriaCorrelatoRepository;
import com.farmacia.farmaciaapi.repository.CorrelatoRepository;
import com.farmacia.farmaciaapi.service.exception.ObjetoInexistenteOuInativoException;

@Service
public class CorrelatoService {
	
	@Autowired
	private CorrelatoRepository correlatoRepository;
	
	@Autowired
	private CategoriaCorrelatoRepository categoriaRepository;

	public Correlato atualizar(Long codigo, Correlato correlato) {

		Correlato correlatoSalvo = buscarCorrelatoSalvo(codigo);

		BeanUtils.copyProperties(correlato, correlatoSalvo, "codigo");

		return this.correlatoRepository.save(correlatoSalvo);
	}
	
	public Correlato salvar(Correlato correlato) {
		Optional<CategoriaCorrelato> categoriaOpt = categoriaRepository.findById(correlato.getCategoriaCorrelato().getCodigo());

	    if (!categoriaOpt.isPresent() || categoriaOpt.get().isInativo()) {
	        throw new ObjetoInexistenteOuInativoException();
	    }
	    
	    return correlatoRepository.save(correlato);
	}
	
	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Correlato correlatoSalvo = buscarCorrelatoSalvo(codigo);
		correlatoSalvo.setAtivo(ativo);
		correlatoRepository.save(correlatoSalvo);
		
	}

	public Correlato buscarCorrelatoSalvo(Long codigo) {
		Correlato correlatoSalvo = this.correlatoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return correlatoSalvo;
	}

}
