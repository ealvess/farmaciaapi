package com.farmacia.farmaciaapi.repository.categoriacorrelato;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.farmacia.farmaciaapi.model.CategoriaCorrelato;
import com.farmacia.farmaciaapi.model.CategoriaCorrelato_;
import com.farmacia.farmaciaapi.repository.filter.CategoriaCorrelatoFilter;

public class CategoriaCorrelatoRepositoryImpl implements CategoriaCorrelatoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<CategoriaCorrelato> filtrar(CategoriaCorrelatoFilter categoriaFilter, Pageable pageable) {
		// Consegue criar as criterias
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<CategoriaCorrelato> criteria = builder.createQuery(CategoriaCorrelato.class);
		Root<CategoriaCorrelato> root = criteria.from(CategoriaCorrelato.class);

		// criar restrições (parametros para a busca)
		Predicate[] predicates = criarRestricoes(categoriaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<CategoriaCorrelato> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(categoriaFilter));
	}

	private Predicate[] criarRestricoes(CategoriaCorrelatoFilter categoriaFilter, CriteriaBuilder builder, Root<CategoriaCorrelato> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(categoriaFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(CategoriaCorrelato_.nome)),
					"%" + categoriaFilter.getNome().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adiconarRestricoesDePaginacao(TypedQuery<CategoriaCorrelato> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistorsPorPagina = pageable.getPageSize();
		int primeiroRegistroPorPagina = paginaAtual * totalDeRegistorsPorPagina;

		query.setFirstResult(primeiroRegistroPorPagina);
		query.setMaxResults(totalDeRegistorsPorPagina);

	}

	private Long total(CategoriaCorrelatoFilter categoriaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<CategoriaCorrelato> root = criteria.from(CategoriaCorrelato.class);

		Predicate[] predicates = criarRestricoes(categoriaFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}


}
