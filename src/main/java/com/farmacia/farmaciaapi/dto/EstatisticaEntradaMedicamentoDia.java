package com.farmacia.farmaciaapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.farmacia.farmaciaapi.model.Medicamento;

public class EstatisticaEntradaMedicamentoDia {

	private Medicamento medicamento;

	private LocalDate dia;

	private BigDecimal quantidade;

	public EstatisticaEntradaMedicamentoDia(Medicamento medicamento,  LocalDate dia, BigDecimal quantidade) {
		this.medicamento = medicamento ;
		this.dia =  (LocalDate) dia;
		this.quantidade = (BigDecimal) quantidade;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

}
