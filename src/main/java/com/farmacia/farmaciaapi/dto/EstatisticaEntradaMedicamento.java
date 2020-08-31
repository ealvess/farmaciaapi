package com.farmacia.farmaciaapi.dto;

import java.math.BigDecimal;

import com.farmacia.farmaciaapi.model.Medicamento;

public class EstatisticaEntradaMedicamento {

	private Medicamento medicamento;

	private BigDecimal quantidade;

	public EstatisticaEntradaMedicamento(Medicamento medicamento, BigDecimal quantidade) {
		this.medicamento = medicamento ;
		this.quantidade = (BigDecimal) quantidade;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

}
