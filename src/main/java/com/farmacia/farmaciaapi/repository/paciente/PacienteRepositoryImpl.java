package com.farmacia.farmaciaapi.repository.paciente;

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

import com.farmacia.farmaciaapi.model.Paciente;
import com.farmacia.farmaciaapi.model.Paciente_;
import com.farmacia.farmaciaapi.repository.filter.PacienteFilter;
import com.farmacia.farmaciaapi.repository.projection.ResumoPaciente;

public class PacienteRepositoryImpl implements PacienteRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Paciente> filtrar(PacienteFilter pacienteFilter, Pageable pageable) {
		// Consegue criar as criterias
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Paciente> criteria = builder.createQuery(Paciente.class);
		Root<Paciente> root = criteria.from(Paciente.class);

		// criar restrições (parametros para a busca)
		Predicate[] predicates = criarRestricoes(pacienteFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Paciente> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(pacienteFilter));
	}
	
	@Override
	public Page<ResumoPaciente> resumo(PacienteFilter pacienteFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoPaciente> criteria = builder.createQuery(ResumoPaciente.class);
		Root<Paciente> root = criteria.from(Paciente.class);
		
		criteria.select(builder.construct(ResumoPaciente.class, 
				root.get(Paciente_.codigo),
				root.get(Paciente_.nome),
				root.get(Paciente_.cpf),
				root.get(Paciente_.rg),
				root.get(Paciente_.dataNascimento),
				root.get(Paciente_.cartaoSus)));
		
		Predicate[] predicates = criarRestricoes(pacienteFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoPaciente> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(pacienteFilter));
	}

	private Predicate[] criarRestricoes(PacienteFilter pacienteFilter, CriteriaBuilder builder, Root<Paciente> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(pacienteFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Paciente_.nome)),
					"%" + pacienteFilter.getNome().toLowerCase() + "%"));
		}
		if (pacienteFilter.getProntuario() != null) {
			predicates.add(builder.equal(root.get(Paciente_.codigo), pacienteFilter.getProntuario()));
		}
		if (pacienteFilter.getDataDeNascimento() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Paciente_.dataNascimento),
					pacienteFilter.getDataDeNascimento()));
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

	private Long total(PacienteFilter pacienteFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Paciente> root = criteria.from(Paciente.class);

		Predicate[] predicates = criarRestricoes(pacienteFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}



	

}
