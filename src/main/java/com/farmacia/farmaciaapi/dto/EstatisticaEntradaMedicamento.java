package com.farmacia.farmaciaapi.dto;

import java.math.BigDecimal;

import com.farmacia.farmaciaapi.model.EntradaMedicamento;
import com.farmacia.farmaciaapi.model.Medicamento;

public class EstatisticaEntradaMedicamento {

	private Medicamento medicamento;

	private BigDecimal total;

	public EstatisticaEntradaMedicamento(Medicamento medicamento, BigDecimal total) {
		this.medicamento = medicamento;
		this.total = total;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
