package com.farmacia.farmaciaapi.repository.categoria;

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

import com.farmacia.farmaciaapi.model.Categoria;
import com.farmacia.farmaciaapi.model.Categoria_;
import com.farmacia.farmaciaapi.repository.filter.CategoriaFilter;

public class CategoriaRepositoryImpl implements CategoriaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Categoria> filtrar(CategoriaFilter categoriaFilter, Pageable pageable) {
		// Consegue criar as criterias
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Categoria> criteria = builder.createQuery(Categoria.class);
		Root<Categoria> root = criteria.from(Categoria.class);

		// criar restrições (parametros para a busca)
		Predicate[] predicates = criarRestricoes(categoriaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Categoria> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(categoriaFilter));
	}

	private Predicate[] criarRestricoes(CategoriaFilter categoriaFilter, CriteriaBuilder builder, Root<Categoria> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(categoriaFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Categoria_.nome)),
					"%" + categoriaFilter.getNome().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adiconarRestricoesDePaginacao(TypedQuery<Categoria> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistorsPorPagina = pageable.getPageSize();
		int primeiroRegistroPorPagina = paginaAtual * totalDeRegistorsPorPagina;

		query.setFirstResult(primeiroRegistroPorPagina);
		query.setMaxResults(totalDeRegistorsPorPagina);

	}

	private Long total(CategoriaFilter categoriaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Categoria> root = criteria.from(Categoria.class);

		Predicate[] predicates = criarRestricoes(categoriaFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}


}
