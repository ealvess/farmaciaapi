package com.farmacia.farmaciaapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.farmacia.farmaciaapi.model.CategoriaCorrelato;
import com.farmacia.farmaciaapi.repository.CategoriaCorrelatoRepository;

@Service
public class CategoriaCorrelatoService {

	@Autowired
	private CategoriaCorrelatoRepository categoriaRepository;

	public CategoriaCorrelato atualizar(Long codigo, CategoriaCorrelato categoria) {

		CategoriaCorrelato categoriaSalva = buscarCategoriaSalva(codigo);

		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");

		return this.categoriaRepository.save(categoriaSalva);
	}
	

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		CategoriaCorrelato categoriaSalva = buscarCategoriaSalva(codigo);
		categoriaSalva.setAtivo(ativo);
		categoriaRepository.save(categoriaSalva);
	}
	

	public CategoriaCorrelato buscarCategoriaSalva(Long codigo) {
		CategoriaCorrelato categoriaSalva = this.categoriaRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return categoriaSalva;
	}



}
