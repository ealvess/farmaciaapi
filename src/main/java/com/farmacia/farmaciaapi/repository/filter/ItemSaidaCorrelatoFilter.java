package com.farmacia.farmaciaapi.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ItemSaidaCorrelatoFilter {

	private String nome;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataSaidaDe;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataSaidaAte;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
