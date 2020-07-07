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

import org.springframework.util.StringUtils;

import com.farmacia.farmaciaapi.model.Medico;
import com.farmacia.farmaciaapi.model.Medico_;
import com.farmacia.farmaciaapi.repository.filter.MedicoFilter;

public class MedicoRepositoryImpl implements MedicoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Medico> filtrar(MedicoFilter medicoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Medico> criteria = builder.createQuery(Medico.class);
		Root<Medico> root = criteria.from(Medico.class);
		
		Predicate[] predicates = criarRestricoes(medicoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Medico> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	private Predicate[] criarRestricoes(MedicoFilter medicoFilter, CriteriaBuilder builder,
			Root<Medico> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(medicoFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get(Medico_.nome)), "%" + medicoFilter.getNome().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(medicoFilter.getCrm())) {
			predicates.add(builder.like(
					builder.lower(root.get(Medico_.crm)), "%" + medicoFilter.getCrm().toLowerCase() + "%"));
		}
				
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
