package com.farmacia.farmaciaapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.farmacia.farmaciaapi.model.EntradaMedicamento;

public class EstatisticaEntradaMedicamentoDia {

	private EntradaMedicamento entradaMedicamento;

	private LocalDate dia;

	private BigDecimal total;

	public EstatisticaEntradaMedicamentoDia(EntradaMedicamento entradaMedicamento, LocalDate dia, BigDecimal total) {
		this.entradaMedicamento = entradaMedicamento;
		this.dia = dia;
		this.total = total;
	}

	public EntradaMedicamento getEntradaMedicamento() {
		return entradaMedicamento;
	}

	public void setEntradaMedicamento(EntradaMedicamento entradaMedicamento) {
		this.entradaMedicamento = entradaMedicamento;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
