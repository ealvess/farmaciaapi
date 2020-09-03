package com.farmacia.farmaciaapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.farmacia.farmaciaapi.model.EntradaMedicamento;

public class EstatisticaSaidaMedicamentoPorCentroDeCusto {

	private EntradaMedicamento medicamento;

	private LocalDate dataSaida;

	private BigDecimal quantidade;

	public EstatisticaSaidaMedicamentoPorCentroDeCusto(EntradaMedicamento medicamento,  LocalDate dataSaida,
			BigDecimal quantidade) {
		this.medicamento = medicamento ;
		this.dataSaida =  (LocalDate) dataSaida;
		this.quantidade = (BigDecimal) quantidade;
	}

	public EntradaMedicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(EntradaMedicamento medicamento) {
		this.medicamento = medicamento;
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
