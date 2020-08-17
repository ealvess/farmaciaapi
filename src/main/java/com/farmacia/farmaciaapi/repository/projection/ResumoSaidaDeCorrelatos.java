package com.farmacia.farmaciaapi.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ResumoSaidaDeCorrelatos {

	private Long codigo;
	private String centrodeCusto;
	private String entradCorrelato;
	private LocalDate dataSaida;
	private Integer quantidade;
	private BigDecimal valorUnitario;

	public ResumoSaidaDeCorrelatos(Long codigo, String centrodeCusto, String entradCorrelato, LocalDate dataSaida,
			Integer quantidade, BigDecimal valorUnitario) {
		super();
		this.codigo = codigo;
		this.centrodeCusto = centrodeCusto;
		this.entradCorrelato = entradCorrelato;
		this.dataSaida = dataSaida;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCentrodeCusto() {
		return centrodeCusto;
	}

	public void setCentrodeCusto(String centrodeCusto) {
		this.centrodeCusto = centrodeCusto;
	}

	public String getEntradCorrelato() {
		return entradCorrelato;
	}

	public void setEntradCorrelato(String entradCorrelato) {
		this.entradCorrelato = entradCorrelato;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
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
