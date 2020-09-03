package com.farmacia.farmaciaapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.farmacia.farmaciaapi.model.EntradaCorrelato;

public class EstatisticaSaidaCorrelatoPorMes {

	private EntradaCorrelato correlato;

	private LocalDate dataSaida;

	private BigDecimal quantidade;

	public EstatisticaSaidaCorrelatoPorMes(EntradaCorrelato correlato, LocalDate dataSaida, BigDecimal quantidade) {
		this.correlato = correlato;
		this.dataSaida = (LocalDate) dataSaida;
		this.quantidade = (BigDecimal) quantidade;
	}

	public EntradaCorrelato getCorrelato() {
		return correlato;
	}

	public void setCorrelato(EntradaCorrelato correlato) {
		this.correlato = correlato;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
}
