package com.farmacia.farmaciaapi.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ItemSaidaMedicamentoPorCentroDeCustoFilter {

	private String centroDeCusto;
	private String nomemedicamento;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataSaidaDe;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataSaidaAte;

	public String getCentroDeCusto() {
		return centroDeCusto;
	}

	public void setCentroDeCusto(String centroDeCusto) {
		this.centroDeCusto = centroDeCusto;
	}

	public String getNomemedicamento() {
		return nomemedicamento;
	}

	public void setNomemedicamento(String nomemedicamento) {
		this.nomemedicamento = nomemedicamento;
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
