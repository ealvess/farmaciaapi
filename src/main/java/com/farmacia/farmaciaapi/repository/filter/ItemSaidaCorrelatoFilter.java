package com.farmacia.farmaciaapi.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ItemSaidaCorrelatoFilter {

	private String nomecorrelato;
	private String centrodecusto;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataSaidaDe;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataSaidaAte;

	public String getNomecorrelato() {
		return nomecorrelato;
	}

	public void setNomecorrelato(String nomecorrelato) {
		this.nomecorrelato = nomecorrelato;
	}

	public String getCentrodecusto() {
		return centrodecusto;
	}

	public void setCentrodecusto(String centrodecusto) {
		this.centrodecusto = centrodecusto;
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
