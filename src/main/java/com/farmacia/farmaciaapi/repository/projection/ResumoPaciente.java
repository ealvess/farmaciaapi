package com.farmacia.farmaciaapi.repository.projection;

import java.time.LocalDate;

public class ResumoPaciente {

	private Long codigo;
	private String nome;
	private String cpf;
	private String rg;
	private LocalDate dataNascimento;
	private String cartaoSus;

	public ResumoPaciente(Long codigo, String nome, String cpf, String rg, LocalDate dataNascimento, String cartaoSus) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.cartaoSus = cartaoSus;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCartaoSus() {
		return cartaoSus;
	}

	public void setCartaoSus(String cartaoSus) {
		this.cartaoSus = cartaoSus;
	}

}
