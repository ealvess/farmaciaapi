package com.farmacia.farmaciaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.farmaciaapi.model.ItemSaidaCorrelato;
import com.farmacia.farmaciaapi.repository.saidacorrelato.ItemSaidaCorrelatoRepositoryQuery;

public interface ItemSaidaCorrelatoRepository extends JpaRepository<ItemSaidaCorrelato, Long>, ItemSaidaCorrelatoRepositoryQuery{

}
