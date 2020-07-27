package com.farmacia.farmaciaapi.repository.medicamento;

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

import com.farmacia.farmaciaapi.model.Medicamento;
import com.farmacia.farmaciaapi.model.Medicamento_;
import com.farmacia.farmaciaapi.repository.filter.MedicamentoFilter;

public class MedicamentoRepositoryImpl implements MedicamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Medicamento> filtrar(MedicamentoFilter medicamentoFilter, Pageable pageable) {
		// Consegue criar as criterias
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Medicamento> criteria = builder.createQuery(Medicamento.class);
		Root<Medicamento> root = criteria.from(Medicamento.class);

		// criar restrições (parametros para a busca)
		Predicate[] predicates = criarRestricoes(medicamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Medicamento> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(medicamentoFilter));
	}

	private Predicate[] criarRestricoes(MedicamentoFilter medicamentoFilter, CriteriaBuilder builder, Root<Medicamento> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(medicamentoFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Medicamento_.nome)),
					"%" + medicamentoFilter.getNome().toLowerCase() + "%"));
		}
		if (medicamentoFilter.getCodigo() != null) {
			predicates.add(builder.equal(root.get(Medicamento_.codigo), medicamentoFilter.getCodigo()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adiconarRestricoesDePaginacao(TypedQuery<Medicamento> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistorsPorPagina = pageable.getPageSize();
		int primeiroRegistroPorPagina = paginaAtual * totalDeRegistorsPorPagina;

		query.setFirstResult(primeiroRegistroPorPagina);
		query.setMaxResults(totalDeRegistorsPorPagina);

	}

	private Long total(MedicamentoFilter medicamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Medicamento> root = criteria.from(Medicamento.class);

		Predicate[] predicates = criarRestricoes(medicamentoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}


}
