package com.farmacia.farmaciaapi.repository.saidacorrelato;

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

import com.farmacia.farmaciaapi.model.ItemSaidaCorrelato;
import com.farmacia.farmaciaapi.model.ItemSaidaCorrelato_;
import com.farmacia.farmaciaapi.repository.filter.ItemSaidaCorrelatoFilter;

public class ItemSaidaCorrelatoRepositoryImpl implements ItemSaidaCorrelatoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<ItemSaidaCorrelato> filtrar(ItemSaidaCorrelatoFilter itemSaidaCorrelatoFilter, Pageable pageable) {
		// Consegue criar as criterias
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ItemSaidaCorrelato> criteria = builder.createQuery(ItemSaidaCorrelato.class);
		Root<ItemSaidaCorrelato> root = criteria.from(ItemSaidaCorrelato.class);

		// criar restrições (parametros para a busca)
		Predicate[] predicates = criarRestricoes(itemSaidaCorrelatoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ItemSaidaCorrelato> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(itemSaidaCorrelatoFilter));
	}

	private Predicate[] criarRestricoes(ItemSaidaCorrelatoFilter itemSaidaCorrelatoFilter, CriteriaBuilder builder,
			Root<ItemSaidaCorrelato> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (itemSaidaCorrelatoFilter.getDataSaidaDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(ItemSaidaCorrelato_.dataSaida),
					itemSaidaCorrelatoFilter.getDataSaidaDe()));
		}

		if (itemSaidaCorrelatoFilter.getDataSaidaAte() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(ItemSaidaCorrelato_.dataSaida),
					itemSaidaCorrelatoFilter.getDataSaidaAte()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adiconarRestricoesDePaginacao(TypedQuery<ItemSaidaCorrelato> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistorsPorPagina = pageable.getPageSize();
		int primeiroRegistroPorPagina = paginaAtual * totalDeRegistorsPorPagina;

		query.setFirstResult(primeiroRegistroPorPagina);
		query.setMaxResults(totalDeRegistorsPorPagina);

	}

	private Long total(ItemSaidaCorrelatoFilter itemSaidaCorrelatoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ItemSaidaCorrelato> root = criteria.from(ItemSaidaCorrelato.class);

		Predicate[] predicates = criarRestricoes(itemSaidaCorrelatoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}

}
