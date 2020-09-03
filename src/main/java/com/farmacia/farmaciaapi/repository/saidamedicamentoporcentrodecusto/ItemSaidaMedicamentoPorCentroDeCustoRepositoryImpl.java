package com.farmacia.farmaciaapi.repository.saidamedicamentoporcentrodecusto;

import java.time.LocalDate;
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

import com.farmacia.farmaciaapi.dto.EstatisticaSaidaMedicamentoPorCentroDeCusto;
import com.farmacia.farmaciaapi.model.CentroDeCusto_;
import com.farmacia.farmaciaapi.model.EntradaMedicamento_;
import com.farmacia.farmaciaapi.model.ItemSaidaMedicamentoPorCentroDeCusto;
import com.farmacia.farmaciaapi.model.ItemSaidaMedicamentoPorCentroDeCusto_;
import com.farmacia.farmaciaapi.model.Medicamento_;
import com.farmacia.farmaciaapi.repository.filter.ItemSaidaMedicamentoPorCentroDeCustoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoSaidaDeMedicamentosPorCentroDeCusto;

public class ItemSaidaMedicamentoPorCentroDeCustoRepositoryImpl implements ItemSaidaMedicamentoPorCentroDeCustoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<EstatisticaSaidaMedicamentoPorCentroDeCusto> porMes(LocalDate mesReferencia) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<EstatisticaSaidaMedicamentoPorCentroDeCusto> criteriaQuery = criteriaBuilder
				.createQuery(EstatisticaSaidaMedicamentoPorCentroDeCusto.class);

		Root<ItemSaidaMedicamentoPorCentroDeCusto> root = criteriaQuery.from(ItemSaidaMedicamentoPorCentroDeCusto.class);

		criteriaQuery.select(criteriaBuilder.construct(EstatisticaSaidaMedicamentoPorCentroDeCusto.class,
				root.get(ItemSaidaMedicamentoPorCentroDeCusto_.entradaMedicamento),
				root.get(ItemSaidaMedicamentoPorCentroDeCusto_.dataSaida),
				criteriaBuilder.sum(root.get(ItemSaidaMedicamentoPorCentroDeCusto_.quantidade))));

		LocalDate primeiroDia = mesReferencia.withDayOfMonth(1);
		LocalDate ultimoDia = mesReferencia.withDayOfMonth(mesReferencia.lengthOfMonth());

		criteriaQuery.where(
				criteriaBuilder.greaterThanOrEqualTo(root.get(ItemSaidaMedicamentoPorCentroDeCusto_.dataSaida), primeiroDia),
				criteriaBuilder.lessThanOrEqualTo(root.get(ItemSaidaMedicamentoPorCentroDeCusto_.dataSaida), ultimoDia));

		criteriaQuery.groupBy(root.get(ItemSaidaMedicamentoPorCentroDeCusto_.entradaMedicamento), root.get(ItemSaidaMedicamentoPorCentroDeCusto_.dataSaida));

		TypedQuery<EstatisticaSaidaMedicamentoPorCentroDeCusto> typedQuery = manager.createQuery(criteriaQuery);

		return typedQuery.getResultList();
	}

	@Override
	public Page<ItemSaidaMedicamentoPorCentroDeCusto> filtrar(ItemSaidaMedicamentoPorCentroDeCustoFilter itemSaidaMedicamentoFilter, Pageable pageable) {
		// Consegue criar as criterias
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ItemSaidaMedicamentoPorCentroDeCusto> criteria = builder.createQuery(ItemSaidaMedicamentoPorCentroDeCusto.class);
		Root<ItemSaidaMedicamentoPorCentroDeCusto> root = criteria.from(ItemSaidaMedicamentoPorCentroDeCusto.class);

		// criar restrições (parametros para a busca)
		Predicate[] predicates = criarRestricoes(itemSaidaMedicamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ItemSaidaMedicamentoPorCentroDeCusto> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(itemSaidaMedicamentoFilter));
	}
	
	@Override
	public Page<ResumoSaidaDeMedicamentosPorCentroDeCusto> resumo(ItemSaidaMedicamentoPorCentroDeCustoFilter itemSaidaMedicamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoSaidaDeMedicamentosPorCentroDeCusto> criteria = builder.createQuery(ResumoSaidaDeMedicamentosPorCentroDeCusto.class);
		Root<ItemSaidaMedicamentoPorCentroDeCusto> root = criteria.from(ItemSaidaMedicamentoPorCentroDeCusto.class);
		
		criteria.select(builder.construct(ResumoSaidaDeMedicamentosPorCentroDeCusto.class, 
				root.get(ItemSaidaMedicamentoPorCentroDeCusto_.codigo),
				root.get(ItemSaidaMedicamentoPorCentroDeCusto_.centrodeCusto).get(CentroDeCusto_.nome),
				root.get(ItemSaidaMedicamentoPorCentroDeCusto_.entradaMedicamento).get(EntradaMedicamento_.medicamento).get(Medicamento_.nome),
				root.get(ItemSaidaMedicamentoPorCentroDeCusto_.dataSaida),
				root.get(ItemSaidaMedicamentoPorCentroDeCusto_.quantidade),
				root.get(ItemSaidaMedicamentoPorCentroDeCusto_.valorUnitario)));
		
		Predicate[] predicates = criarRestricoes(itemSaidaMedicamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoSaidaDeMedicamentosPorCentroDeCusto> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(itemSaidaMedicamentoFilter));
	}

	private Predicate[] criarRestricoes(ItemSaidaMedicamentoPorCentroDeCustoFilter itemSaidaMedicamentoFilter, CriteriaBuilder builder,
			Root<ItemSaidaMedicamentoPorCentroDeCusto> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(itemSaidaMedicamentoFilter.getNomemedicamento())) {
			predicates.add(builder.like(builder.lower(root.get(ItemSaidaMedicamentoPorCentroDeCusto_.entradaMedicamento).get(EntradaMedicamento_.medicamento).get(Medicamento_.nome)),
					"%" + itemSaidaMedicamentoFilter.getNomemedicamento().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(itemSaidaMedicamentoFilter.getCentroDeCusto())) {
			predicates.add(builder.like(builder.lower(root.get(ItemSaidaMedicamentoPorCentroDeCusto_.centrodeCusto).get(CentroDeCusto_.nome)),
					"%" + itemSaidaMedicamentoFilter.getCentroDeCusto().toLowerCase() + "%"));
		}
		if (itemSaidaMedicamentoFilter.getDataSaidaDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(ItemSaidaMedicamentoPorCentroDeCusto_.dataSaida),
					itemSaidaMedicamentoFilter.getDataSaidaDe()));
		}

		if (itemSaidaMedicamentoFilter.getDataSaidaAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(ItemSaidaMedicamentoPorCentroDeCusto_.dataSaida), 
							itemSaidaMedicamentoFilter.getDataSaidaAte()));
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
	

	private Long total(ItemSaidaMedicamentoPorCentroDeCustoFilter itemSaidaMedicamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ItemSaidaMedicamentoPorCentroDeCusto> root = criteria.from(ItemSaidaMedicamentoPorCentroDeCusto.class);
		
		Predicate[] predicates = criarRestricoes(itemSaidaMedicamentoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

}
