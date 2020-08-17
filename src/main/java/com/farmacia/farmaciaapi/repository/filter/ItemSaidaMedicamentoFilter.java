package com.farmacia.farmaciaapi.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ItemSaidaMedicamentoFilter {

	private String nomepaciente;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataSaidaDe;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataSaidaAte;

	public String getNomepaciente() {
		return nomepaciente;
	}

	public void setNomepaciente(String nomepaciente) {
		this.nomepaciente = nomepaciente;
	}

	public LocalDate getDataSaidaDe() {
		return dataSaidaDe;
	}

	public void setDataSaidaDe(LocalDate dataSaidaDe) {
		this.dataSaidaDe = dataSaidaDe;
	}

	public LocalDate getDataSaidaAte() {
		return dataSaidaAte;
	}

	public void setDataSaidaAte(LocalDate dataSaidaAte) {
		this.dataSaidaAte = dataSaidaAte;
	}

}
