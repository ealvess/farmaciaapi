package com.farmacia.farmaciaapi.repository.entradacorrelato;

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

import com.farmacia.farmaciaapi.model.Correlato_;
import com.farmacia.farmaciaapi.model.EntradaCorrelato;
import com.farmacia.farmaciaapi.model.EntradaCorrelato_;
import com.farmacia.farmaciaapi.repository.filter.EntradaCorrelatoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoEntradaCorrelatos;

public class EntradaCorrelatoRepositoryImpl implements EntradaCorrelatoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<EntradaCorrelato> filtrar(EntradaCorrelatoFilter entradaCorrelatoFilter, Pageable pageable) {
		// Consegue criar as criterias
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<EntradaCorrelato> criteria = builder.createQuery(EntradaCorrelato.class);
		Root<EntradaCorrelato> root = criteria.from(EntradaCorrelato.class);

		// criar restrições (parametros para a busca)
		Predicate[] predicates = criarRestricoes(entradaCorrelatoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<EntradaCorrelato> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(entradaCorrelatoFilter));
	}

	@Override
	public Page<ResumoEntradaCorrelatos> resumo(EntradaCorrelatoFilter entradaCorrelatoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoEntradaCorrelatos> criteria = builder.createQuery(ResumoEntradaCorrelatos.class);
		Root<EntradaCorrelato> root = criteria.from(EntradaCorrelato.class);

		criteria.select(builder.construct(ResumoEntradaCorrelatos.class,
				root.get(EntradaCorrelato_.codigo), 
				root.get(EntradaCorrelato_.correlato).get(Correlato_.nome),
				root.get(EntradaCorrelato_.correlato).get(Correlato_.unidadeDeMedida),
				root.get(EntradaCorrelato_.dataEntrada),
				root.get(EntradaCorrelato_.dataValidade), 
				root.get(EntradaCorrelato_.quantidade),
				root.get(EntradaCorrelato_.valorUnitario) ));

		Predicate[] predicates = criarRestricoes(entradaCorrelatoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoEntradaCorrelatos> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(entradaCorrelatoFilter));
	}

	private Predicate[] criarRestricoes(EntradaCorrelatoFilter entradaCorrelatoFilter, CriteriaBuilder builder,
			Root<EntradaCorrelato> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(entradaCorrelatoFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(EntradaCorrelato_.correlato).get(Correlato_.nome)),
					"%" + entradaCorrelatoFilter.getNome().toLowerCase() + "%"));
		}
		if (entradaCorrelatoFilter.getDataValidadeDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(EntradaCorrelato_.dataValidade),
					entradaCorrelatoFilter.getDataValidadeDe()));
		}

		if (entradaCorrelatoFilter.getDataValidadeAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(EntradaCorrelato_.dataValidade), 
							entradaCorrelatoFilter.getDataValidadeAte()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adiconarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistorsPorPagina = pageable.getPageSize();
		int primeiroRegistroPorPagina = paginaAtual * totalDeRegistorsPorPagina;

		query.setFirstResult(primeiroRegistroPorPagina);
		query.setMaxResults(totalDeRegistorsPorPagina);

	}

	private Long total(EntradaCorrelatoFilter entradaCorrelatoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<EntradaCorrelato> root = criteria.from(EntradaCorrelato.class);

		Predicate[] predicates = criarRestricoes(entradaCorrelatoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}

}
