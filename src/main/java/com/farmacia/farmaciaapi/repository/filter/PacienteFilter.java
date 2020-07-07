package com.farmacia.farmaciaapi.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class PacienteFilter {

	private Long prontuario;

	private String nome;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDeNascimento;

	public Long getProntuario() {
		return prontuario;
	}

	public void setProntuario(Long prontuario) {
		this.prontuario = prontuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

}
