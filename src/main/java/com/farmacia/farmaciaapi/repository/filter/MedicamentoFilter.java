package com.farmacia.farmaciaapi.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class MedicamentoFilter {

	private String nome;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataValidadeDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataValidadeAte;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataValidadeDe() {
		return dataValidadeDe;
	}

	public void setDataValidadeDe(LocalDate dataValidadeDe) {
		this.dataValidadeDe = dataValidadeDe;
	}

	public LocalDate getDataValidadeAte() {
		return dataValidadeAte;
	}

	public void setDataValidadeAte(LocalDate dataValidadeAte) {
		this.dataValidadeAte = dataValidadeAte;
	}

}
