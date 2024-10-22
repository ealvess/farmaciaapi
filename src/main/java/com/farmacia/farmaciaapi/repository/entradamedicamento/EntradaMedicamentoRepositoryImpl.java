package com.farmacia.farmaciaapi.repository.entradamedicamento;

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

import com.farmacia.farmaciaapi.dto.EstatisticaEntradaMedicamentoPorMes;
import com.farmacia.farmaciaapi.dto.EstatisticaEntradaMedicamentoDia;
import com.farmacia.farmaciaapi.model.EntradaMedicamento;
import com.farmacia.farmaciaapi.model.EntradaMedicamento_;
import com.farmacia.farmaciaapi.model.Medicamento_;
import com.farmacia.farmaciaapi.repository.filter.EntradaMedicamentoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoEntradaMedicamento;

public class EntradaMedicamentoRepositoryImpl implements EntradaMedicamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<EstatisticaEntradaMedicamentoPorMes> porMes(LocalDate inicio, LocalDate fim) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<EstatisticaEntradaMedicamentoPorMes> criteriaQuery = criteriaBuilder
				.createQuery(EstatisticaEntradaMedicamentoPorMes.class);

		Root<EntradaMedicamento> root = criteriaQuery.from(EntradaMedicamento.class);

		criteriaQuery.select(criteriaBuilder.construct(EstatisticaEntradaMedicamentoPorMes.class,
				root.get(EntradaMedicamento_.medicamento),
				criteriaBuilder.sum(root.get(EntradaMedicamento_.quantidade))));
		
		criteriaQuery.where(
				criteriaBuilder.greaterThanOrEqualTo(root.get(EntradaMedicamento_.dataEntrada), 
						inicio),
				criteriaBuilder.lessThanOrEqualTo(root.get(EntradaMedicamento_.dataEntrada), 
						fim));

		
		criteriaQuery.groupBy(root.get(EntradaMedicamento_.medicamento));

		TypedQuery<EstatisticaEntradaMedicamentoPorMes> typedQuery = manager.createQuery(criteriaQuery);

		return typedQuery.getResultList();

	}

	@Override
	public List<EstatisticaEntradaMedicamentoDia> porDia(LocalDate mesReferencia) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<EstatisticaEntradaMedicamentoDia> criteriaQuery = criteriaBuilder
				.createQuery(EstatisticaEntradaMedicamentoDia.class);

		Root<EntradaMedicamento> root = criteriaQuery.from(EntradaMedicamento.class);

		criteriaQuery.select(criteriaBuilder.construct(EstatisticaEntradaMedicamentoDia.class,
				root.get(EntradaMedicamento_.medicamento), root.get(EntradaMedicamento_.dataEntrada),
				criteriaBuilder.sum(root.get(EntradaMedicamento_.quantidade))));

		LocalDate primeiroDia = mesReferencia.withDayOfMonth(1);
		LocalDate ultimoDia = mesReferencia.withDayOfMonth(mesReferencia.lengthOfMonth());

		criteriaQuery.where(
				criteriaBuilder.greaterThanOrEqualTo(root.get(EntradaMedicamento_.dataEntrada), primeiroDia),
				criteriaBuilder.lessThanOrEqualTo(root.get(EntradaMedicamento_.dataEntrada), ultimoDia));

		criteriaQuery.groupBy(root.get(EntradaMedicamento_.medicamento), root.get(EntradaMedicamento_.dataEntrada));

		TypedQuery<EstatisticaEntradaMedicamentoDia> typedQuery = manager.createQuery(criteriaQuery);

		return typedQuery.getResultList();
	}

	@Override
	public List<EstatisticaEntradaMedicamentoPorMes> porMedicamento(LocalDate mesReferencia) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<EstatisticaEntradaMedicamentoPorMes> criteriaQuery = criteriaBuilder
				.createQuery(EstatisticaEntradaMedicamentoPorMes.class);

		Root<EntradaMedicamento> root = criteriaQuery.from(EntradaMedicamento.class);

		criteriaQuery.select(criteriaBuilder.construct(EstatisticaEntradaMedicamentoPorMes.class,
				root.get(EntradaMedicamento_.medicamento),

				criteriaBuilder.sum(root.get(EntradaMedicamento_.quantidade))));

		LocalDate primeiroDia = mesReferencia.withDayOfMonth(1);
		LocalDate ultimoDia = mesReferencia.withDayOfMonth(mesReferencia.lengthOfMonth());

		criteriaQuery.where(
				criteriaBuilder.greaterThanOrEqualTo(root.get(EntradaMedicamento_.dataEntrada), primeiroDia),
				criteriaBuilder.lessThanOrEqualTo(root.get(EntradaMedicamento_.dataEntrada), ultimoDia));

		criteriaQuery.groupBy(root.get(EntradaMedicamento_.medicamento));

		TypedQuery<EstatisticaEntradaMedicamentoPorMes> typedQuery = manager.createQuery(criteriaQuery);

		return typedQuery.getResultList();
	}

	@Override
	public Page<EntradaMedicamento> filtrar(EntradaMedicamentoFilter entradaMedicamentoFilter, Pageable pageable) {
		// Consegue criar as criterias
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<EntradaMedicamento> criteria = builder.createQuery(EntradaMedicamento.class);
		Root<EntradaMedicamento> root = criteria.from(EntradaMedicamento.class);

		// criar restrições (parametros para a busca)
		Predicate[] predicates = criarRestricoes(entradaMedicamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<EntradaMedicamento> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(entradaMedicamentoFilter));
	}

	@Override
	public Page<ResumoEntradaMedicamento> resumo(EntradaMedicamentoFilter entradaMedicamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoEntradaMedicamento> criteria = builder.createQuery(ResumoEntradaMedicamento.class);
		Root<EntradaMedicamento> root = criteria.from(EntradaMedicamento.class);

		criteria.select(builder.construct(ResumoEntradaMedicamento.class, root.get(EntradaMedicamento_.codigo),
				root.get(EntradaMedicamento_.medicamento).get(Medicamento_.nome),
				root.get(EntradaMedicamento_.medicamento).get(Medicamento_.unidadeDeMedida),
				root.get(EntradaMedicamento_.dataEntrada), root.get(EntradaMedicamento_.validade),
				root.get(EntradaMedicamento_.quantidade), root.get(EntradaMedicamento_.valorUnitario)));

		Predicate[] predicates = criarRestricoes(entradaMedicamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoEntradaMedicamento> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(entradaMedicamentoFilter));
	}

	private Predicate[] criarRestricoes(EntradaMedicamentoFilter entradaMedicamentoFilter, CriteriaBuilder builder,
			Root<EntradaMedicamento> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(entradaMedicamentoFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(EntradaMedicamento_.medicamento).get(Medicamento_.nome)),
					"%" + entradaMedicamentoFilter.getNome().toLowerCase() + "%"));
		}
		if (entradaMedicamentoFilter.getDataValidadeDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(EntradaMedicamento_.validade),
					entradaMedicamentoFilter.getDataValidadeDe()));
		}

		if (entradaMedicamentoFilter.getDataValidadeAte() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(EntradaMedicamento_.validade),
					entradaMedicamentoFilter.getDataValidadeAte()));
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

	private Long total(EntradaMedicamentoFilter entradaMedicamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<EntradaMedicamento> root = criteria.from(EntradaMedicamento.class);

		Predicate[] predicates = criarRestricoes(entradaMedicamentoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}

}
