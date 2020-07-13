package com.farmacia.farmaciaapi.repository.correlato;

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

import com.farmacia.farmaciaapi.model.Correlato;
import com.farmacia.farmaciaapi.model.Correlato_;
import com.farmacia.farmaciaapi.model.GrupoCorrelato_;
import com.farmacia.farmaciaapi.repository.filter.CorrelatoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoCorrelatos;

public class CorrelatoRepositoryImpl implements CorrelatoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Correlato> filtrar(CorrelatoFilter correlatoFilter, Pageable pageable) {
		// Consegue criar as criterias
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Correlato> criteria = builder.createQuery(Correlato.class);
		Root<Correlato> root = criteria.from(Correlato.class);

		// criar restrições (parametros para a busca)
		Predicate[] predicates = criarRestricoes(correlatoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Correlato> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(correlatoFilter));
	}

	@Override
	public Page<ResumoCorrelatos> resumo(CorrelatoFilter correlatoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoCorrelatos> criteria = builder.createQuery(ResumoCorrelatos.class);
		Root<Correlato> root = criteria.from(Correlato.class);

		criteria.select(builder.construct(ResumoCorrelatos.class, root.get(Correlato_.codigo),
				root.get(Correlato_.nomeCorrelato), root.get(Correlato_.codigo_grupo).get(GrupoCorrelato_.nome),
				root.get(Correlato_.apresentacao), root.get(Correlato_.dataEntrada), root.get(Correlato_.dataValidade),
				root.get(Correlato_.quantidade), root.get(Correlato_.valorUnitario)));

		Predicate[] predicates = criarRestricoes(correlatoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoCorrelatos> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(correlatoFilter));
	}

	private Predicate[] criarRestricoes(CorrelatoFilter correlatoFilter, CriteriaBuilder builder,
			Root<Correlato> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(correlatoFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Correlato_.nomeCorrelato)),
					"%" + correlatoFilter.getNome().toLowerCase() + "%"));
		}
		if (correlatoFilter.getDataValidadeDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Correlato_.dataValidade),
					correlatoFilter.getDataValidadeDe()));
		}

		if (correlatoFilter.getDataValidadeAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Correlato_.dataValidade), correlatoFilter.getDataValidadeAte()));
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

	private Long total(CorrelatoFilter correlatoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Correlato> root = criteria.from(Correlato.class);

		Predicate[] predicates = criarRestricoes(correlatoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}

}
