package com.farmacia.farmaciaapi.repository.medico;

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

import com.farmacia.farmaciaapi.model.Medico;
import com.farmacia.farmaciaapi.model.Medico_;
import com.farmacia.farmaciaapi.repository.filter.MedicoFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoMedicos;

public class MedicoRepositoryImpl implements MedicoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Medico> filtrar(MedicoFilter medicoFilter, Pageable pageable) {
		// Consegue criar as criterias
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Medico> criteria = builder.createQuery(Medico.class);
		Root<Medico> root = criteria.from(Medico.class);

		// criar restrições (parametros para a busca)
		Predicate[] predicates = criarRestricoes(medicoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Medico> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(medicoFilter));
	}

	@Override
	public Page<ResumoMedicos> resumo(MedicoFilter medicoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoMedicos> criteria = builder.createQuery(ResumoMedicos.class);
		Root<Medico> root = criteria.from(Medico.class);
		
		criteria.select(builder.construct(ResumoMedicos.class, 
				root.get(Medico_.codigo),
				root.get(Medico_.nome),
				root.get(Medico_.crm),
				root.get(Medico_.email),
				root.get(Medico_.telefone),
				root.get(Medico_.celular),
				root.get(Medico_.ativo)));
		
		Predicate[] predicates = criarRestricoes(medicoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoMedicos> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(medicoFilter));
	}

	private Predicate[] criarRestricoes(MedicoFilter medicoFilter, CriteriaBuilder builder,
			Root<Medico> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(medicoFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Medico_.nome)),
					"%" + medicoFilter.getNome().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(medicoFilter.getCrm())) {
			predicates.add(builder.like(builder.lower(root.get(Medico_.crm)),
					"%" + medicoFilter.getCrm().toLowerCase() + "%"));
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

	private Long total(MedicoFilter medicoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Medico> root = criteria.from(Medico.class);

		Predicate[] predicates = criarRestricoes(medicoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}

}
