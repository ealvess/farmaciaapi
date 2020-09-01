package com.farmacia.farmaciaapi.dto;

import java.math.BigDecimal;

import com.farmacia.farmaciaapi.model.Correlato;

public class EstatisticaEntradaCorrelatoPorMes {

	private Correlato correlato;

	private BigDecimal quantidade;

	public EstatisticaEntradaCorrelatoPorMes(Correlato correlato, BigDecimal quantidade) {
		this.correlato = correlato;
		this.quantidade = (BigDecimal) quantidade;
	}

	public Correlato getCorrelato() {
		return correlato;
	}

	public void setCorrelato(Correlato correlato) {
		this.correlato = correlato;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

}
