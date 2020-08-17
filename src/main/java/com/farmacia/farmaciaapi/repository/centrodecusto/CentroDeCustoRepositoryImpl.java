package com.farmacia.farmaciaapi.repository.centrodecusto;

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

import com.farmacia.farmaciaapi.model.CentroDeCusto;
import com.farmacia.farmaciaapi.model.CentroDeCusto_;
import com.farmacia.farmaciaapi.repository.filter.CentroDeCustoFilter;

public class CentroDeCustoRepositoryImpl implements CentroDeCustoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<CentroDeCusto> filtrar(CentroDeCustoFilter centroDeCustoFilter, Pageable pageable) {
		// Consegue criar as criterias
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<CentroDeCusto> criteria = builder.createQuery(CentroDeCusto.class);
		Root<CentroDeCusto> root = criteria.from(CentroDeCusto.class);

		// criar restrições (parametros para a busca)
		Predicate[] predicates = criarRestricoes(centroDeCustoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<CentroDeCusto> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(centroDeCustoFilter));
	}

	private Predicate[] criarRestricoes(CentroDeCustoFilter centroDeCustoFilter, 
			CriteriaBuilder builder, Root<CentroDeCusto> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(centroDeCustoFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(CentroDeCusto_.nome)),
					"%" + centroDeCustoFilter.getNome().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adiconarRestricoesDePaginacao(TypedQuery<CentroDeCusto> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistorsPorPagina = pageable.getPageSize();
		int primeiroRegistroPorPagina = paginaAtual * totalDeRegistorsPorPagina;

		query.setFirstResult(primeiroRegistroPorPagina);
		query.setMaxResults(totalDeRegistorsPorPagina);

	}

	private Long total(CentroDeCustoFilter centroDeCustoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<CentroDeCusto> root = criteria.from(CentroDeCusto.class);

		Predicate[] predicates = criarRestricoes(centroDeCustoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}


}
