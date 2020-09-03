package com.farmacia.farmaciaapi.repository.saidamedicamento;

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

import com.farmacia.farmaciaapi.dto.EstatisticaSaidaMedicamentoPorPaciente;
import com.farmacia.farmaciaapi.model.EntradaMedicamento_;
import com.farmacia.farmaciaapi.model.ItemSaidaMedicamento;
import com.farmacia.farmaciaapi.model.ItemSaidaMedicamento_;
import com.farmacia.farmaciaapi.model.Medicamento_;
import com.farmacia.farmaciaapi.model.Paciente_;
import com.farmacia.farmaciaapi.repository.filter.ItemSaidaMedicamentoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoSaidaDeMedicamentos;

public class ItemSaidaMedicamentoRepositoryImpl implements ItemSaidaMedicamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<EstatisticaSaidaMedicamentoPorPaciente> porMes(LocalDate mesReferencia) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<EstatisticaSaidaMedicamentoPorPaciente> criteriaQuery = criteriaBuilder
				.createQuery(EstatisticaSaidaMedicamentoPorPaciente.class);

		Root<ItemSaidaMedicamento> root = criteriaQuery.from(ItemSaidaMedicamento.class);

		criteriaQuery.select(criteriaBuilder.construct(EstatisticaSaidaMedicamentoPorPaciente.class,
				root.get(ItemSaidaMedicamento_.entradaMedicamento),
				root.get(ItemSaidaMedicamento_.dataSaida),
				criteriaBuilder.sum(root.get(ItemSaidaMedicamento_.quantidade))));

		LocalDate primeiroDia = mesReferencia.withDayOfMonth(1);
		LocalDate ultimoDia = mesReferencia.withDayOfMonth(mesReferencia.lengthOfMonth());

		criteriaQuery.where(
				criteriaBuilder.greaterThanOrEqualTo(root.get(ItemSaidaMedicamento_.dataSaida), primeiroDia),
				criteriaBuilder.lessThanOrEqualTo(root.get(ItemSaidaMedicamento_.dataSaida), ultimoDia));

		criteriaQuery.groupBy(root.get(ItemSaidaMedicamento_.entradaMedicamento), root.get(ItemSaidaMedicamento_.dataSaida));

		TypedQuery<EstatisticaSaidaMedicamentoPorPaciente> typedQuery = manager.createQuery(criteriaQuery);

		return typedQuery.getResultList();
	}

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
	
	@Override
	public Page<ResumoSaidaDeMedicamentos> resumo(ItemSaidaMedicamentoFilter itemSaidaMedicamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoSaidaDeMedicamentos> criteria = builder.createQuery(ResumoSaidaDeMedicamentos.class);
		Root<ItemSaidaMedicamento> root = criteria.from(ItemSaidaMedicamento.class);
		
		criteria.select(builder.construct(ResumoSaidaDeMedicamentos.class, 
				root.get(ItemSaidaMedicamento_.codigo),
				root.get(ItemSaidaMedicamento_.paciente).get(Paciente_.nome),
				root.get(ItemSaidaMedicamento_.entradaMedicamento).get(EntradaMedicamento_.medicamento).get(Medicamento_.nome),
				root.get(ItemSaidaMedicamento_.dataSaida),
				root.get(ItemSaidaMedicamento_.quantidade),
				root.get(ItemSaidaMedicamento_.valorUnitario)));
		
		Predicate[] predicates = criarRestricoes(itemSaidaMedicamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoSaidaDeMedicamentos> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(itemSaidaMedicamentoFilter));
	}

	private Predicate[] criarRestricoes(ItemSaidaMedicamentoFilter itemSaidaMedicamentoFilter, CriteriaBuilder builder,
			Root<ItemSaidaMedicamento> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(itemSaidaMedicamentoFilter.getNomepaciente())) {
			predicates.add(builder.like(builder.lower(root.get(ItemSaidaMedicamento_.paciente).get(Paciente_.nome)),
					"%" + itemSaidaMedicamentoFilter.getNomepaciente().toLowerCase() + "%"));
		}
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

	private void adiconarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
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
