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
import org.springframework.util.StringUtils;

import com.farmacia.farmaciaapi.model.CentroDeCusto_;
import com.farmacia.farmaciaapi.model.Correlato_;
import com.farmacia.farmaciaapi.model.EntradaCorrelato_;
import com.farmacia.farmaciaapi.model.ItemSaidaCorrelato;
import com.farmacia.farmaciaapi.model.ItemSaidaCorrelato_;
import com.farmacia.farmaciaapi.repository.filter.ItemSaidaCorrelatoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoSaidaDeCorrelatos;

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
	
	@Override
	public Page<ResumoSaidaDeCorrelatos> resumo(ItemSaidaCorrelatoFilter itemSaidaCorrelatoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoSaidaDeCorrelatos> criteria = builder.createQuery(ResumoSaidaDeCorrelatos.class);
		Root<ItemSaidaCorrelato> root = criteria.from(ItemSaidaCorrelato.class);
		
		criteria.select(builder.construct(ResumoSaidaDeCorrelatos.class, 
				root.get(ItemSaidaCorrelato_.codigo),
				root.get(ItemSaidaCorrelato_.centroDeCusto).get(CentroDeCusto_.nome),
				root.get(ItemSaidaCorrelato_.entradaCorrelato).get(EntradaCorrelato_.correlato).get(Correlato_.nome),
				root.get(ItemSaidaCorrelato_.dataSaida),
				root.get(ItemSaidaCorrelato_.quantidade),
				root.get(ItemSaidaCorrelato_.valorUnitario)));
		
		Predicate[] predicates = criarRestricoes(itemSaidaCorrelatoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoSaidaDeCorrelatos> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(itemSaidaCorrelatoFilter));
	}

	private Predicate[] criarRestricoes(ItemSaidaCorrelatoFilter itemSaidaCorrelatoFilter, CriteriaBuilder builder,
			Root<ItemSaidaCorrelato> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(itemSaidaCorrelatoFilter.getNomecorrelato())) {
			predicates.add(builder.like(builder.lower(root.get(ItemSaidaCorrelato_.entradaCorrelato).get(EntradaCorrelato_.correlato).get(Correlato_.nome)),
					"%" + itemSaidaCorrelatoFilter.getNomecorrelato().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(itemSaidaCorrelatoFilter.getCentrodecusto())) {
			predicates.add(builder.like(builder.lower(root.get(ItemSaidaCorrelato_.centroDeCusto).get(CentroDeCusto_.nome)),
					"%" + itemSaidaCorrelatoFilter.getCentrodecusto().toLowerCase() + "%"));
		}
		if (itemSaidaCorrelatoFilter.getDataSaidaDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(ItemSaidaCorrelato_.dataSaida),
					itemSaidaCorrelatoFilter.getDataSaidaDe()));
		}

		if (itemSaidaCorrelatoFilter.getDataSaidaAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(ItemSaidaCorrelato_.dataSaida), 
							itemSaidaCorrelatoFilter.getDataSaidaAte()));
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
