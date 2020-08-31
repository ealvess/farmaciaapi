package com.farmacia.farmaciaapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.farmacia.farmaciaapi.model.EntradaMedicamento;
import com.farmacia.farmaciaapi.model.Paciente;

public class EstatisticaSaidaMedicamentoPorPaciente {

	private EntradaMedicamento medicamento;

	private Paciente paciente;

	private LocalDate dataSaida;

	private BigDecimal quantidade;

	public EstatisticaSaidaMedicamentoPorPaciente(EntradaMedicamento medicamento, Paciente paciente, LocalDate dataSaida,
			BigDecimal quantidade) {
		this.medicamento = medicamento ;
		this.paciente = paciente;
		this.dataSaida =  (LocalDate) dataSaida;
		this.quantidade = (BigDecimal) quantidade;
	}

	public EntradaMedicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(EntradaMedicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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
