package com.farmacia.farmaciaapi.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ResumoMedicamento {

	private Long codigo;
	private String nomeMedicamento;
	private String categoria;
	private String apresentacao;
	private LocalDate dataEntrada;
	private LocalDate dataValidade;
	private Integer quantidade;
	private BigDecimal valorUnitario;

	public ResumoMedicamento(Long codigo, String nomeMedicamento, String categoria, String apresentacao,
			LocalDate dataEntrada, LocalDate dataValidade, Integer quantidade, BigDecimal valorUnitario) {
		super();
		this.codigo = codigo;
		this.nomeMedicamento = nomeMedicamento;
		this.categoria = categoria;
		this.apresentacao = apresentacao;
		this.dataEntrada = dataEntrada;
		this.dataValidade = dataValidade;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNomeMedicamento() {
		return nomeMedicamento;
	}

	public void setNomeMedicamento(String nomeMedicamento) {
		this.nomeMedicamento = nomeMedicamento;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getApresentacao() {
		return apresentacao;
	}

	public void setApresentacao(String apresentacao) {
		this.apresentacao = apresentacao;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

}
