package com.farmacia.farmaciaapi.repository.saidamedicamento;

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

import com.farmacia.farmaciaapi.model.ItemSaidaMedicamento;
import com.farmacia.farmaciaapi.model.ItemSaidaMedicamento_;
import com.farmacia.farmaciaapi.repository.filter.ItemSaidaMedicamentoFilter;

public class ItemSaidaMedicamentoRepositoryImpl implements ItemSaidaMedicamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<ItemSaidaMedicamento> filtrar(ItemSaidaMedicamentoFilter itemSaidaMedicamentoFilter, Pageable pageable) {
		// Consegue criar as criterias
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ItemSaidaMedicamento> criteria = builder.createQuery(ItemSaidaMedicamento.class);
		Root<ItemSaidaMedicamento> root = criteria.from(ItemSaidaMedicamento.class);

		// criar restrições (parametros para a busca)
		Predicate[] predicates = criarRestricoes(itemSaidaMedicamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ItemSaidaMedicamento> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(itemSaidaMedicamentoFilter));
	}

	private Predicate[] criarRestricoes(ItemSaidaMedicamentoFilter itemSaidaMedicamentoFilter, CriteriaBuilder builder,
			Root<ItemSaidaMedicamento> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (itemSaidaMedicamentoFilter.getDataSaidaDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(ItemSaidaMedicamento_.dataSaida),
					itemSaidaMedicamentoFilter.getDataSaidaDe()));
		}

		if (itemSaidaMedicamentoFilter.getDataSaidaAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(ItemSaidaMedicamento_.dataSaida), 
							itemSaidaMedicamentoFilter.getDataSaidaAte()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adiconarRestricoesDePaginacao(TypedQuery<ItemSaidaMedicamento> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistorsPorPagina = pageable.getPageSize();
		int primeiroRegistroPorPagina = paginaAtual * totalDeRegistorsPorPagina;
		
		query.setFirstResult(primeiroRegistroPorPagina);
		query.setMaxResults(totalDeRegistorsPorPagina);

	}
	

	private Long total(ItemSaidaMedicamentoFilter itemSaidaMedicamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ItemSaidaMedicamento> root = criteria.from(ItemSaidaMedicamento.class);
		
		Predicate[] predicates = criarRestricoes(itemSaidaMedicamentoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

}
