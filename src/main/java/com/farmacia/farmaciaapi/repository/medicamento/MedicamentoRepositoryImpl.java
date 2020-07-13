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

import com.farmacia.farmaciaapi.model.Categoria_;
import com.farmacia.farmaciaapi.model.Medicamento;
import com.farmacia.farmaciaapi.model.Medicamento_;
import com.farmacia.farmaciaapi.repository.filter.MedicamentoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoMedicamento;

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
	
	@Override
	public Page<ResumoMedicamento> resumo(MedicamentoFilter medicamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoMedicamento> criteria = builder.createQuery(ResumoMedicamento.class);
		Root<Medicamento> root = criteria.from(Medicamento.class);
		
		criteria.select(builder.construct(ResumoMedicamento.class, 
				root.get(Medicamento_.codigo), root.get(Medicamento_.nomeMedicamento),
				root.get(Medicamento_.Categoria).get(Categoria_.nome),
				root.get(Medicamento_.apresentacaoMedicamento), root.get(Medicamento_.dataEntrada),
				root.get(Medicamento_.validade), root.get(Medicamento_.quantidade),
				root.get(Medicamento_.valorUnitario) ));
		
		Predicate[] predicates = criarRestricoes(medicamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoMedicamento> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(medicamentoFilter));
	}

	private Predicate[] criarRestricoes(MedicamentoFilter medicamentoFilter, CriteriaBuilder builder,
			Root<Medicamento> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(medicamentoFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Medicamento_.nomeMedicamento)),
					"%" + medicamentoFilter.getNome().toLowerCase() + "%"));
		}
		if (medicamentoFilter.getDataValidadeDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Medicamento_.validade),
					medicamentoFilter.getDataValidadeDe()));
		}

		if (medicamentoFilter.getDataValidadeAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Medicamento_.validade), medicamentoFilter.getDataValidadeAte()));
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
