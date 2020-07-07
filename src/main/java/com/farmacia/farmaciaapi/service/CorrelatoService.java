package com.farmacia.farmaciaapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.model.Correlato;
import com.farmacia.farmaciaapi.repository.CorrelatoRepository;

@Service
public class CorrelatoService {

	@Autowired
	private CorrelatoRepository correlatoRepository;

	private Integer qtdNova;

	public Correlato atualizar(Long codigo, Correlato correlato) {

		Correlato correlatoSalvo = buscarCorrelatoPeloCodigo(codigo);

		BeanUtils.copyProperties(correlato, correlatoSalvo, "codigo");

		return this.correlatoRepository.save(correlatoSalvo);
	}

	public void atualizarQuantidade(Long codigo, Integer quantidade) {
		Correlato correlatoSalvo = buscarCorrelatoPeloCodigo(codigo);
		qtdNova = correlatoSalvo.getQuantidade() - quantidade;
		correlatoSalvo.setQuantidade(qtdNova);
		correlatoRepository.save(correlatoSalvo);
	}

	private Correlato buscarCorrelatoPeloCodigo(Long codigo) {
		Correlato correlatoSalvo = this.correlatoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return correlatoSalvo;
	}

}
