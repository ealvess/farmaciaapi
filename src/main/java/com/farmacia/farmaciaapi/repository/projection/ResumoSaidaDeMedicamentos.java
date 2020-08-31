package com.farmacia.farmaciaapi.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ResumoSaidaDeMedicamentos {

	private Long codigo;
	private String paciente;
	private String centrodeCusto;
	private String entradaMedicamento;
	private LocalDate dataSaida;
	private Integer quantidade;
	private BigDecimal valorUnitario;

	public ResumoSaidaDeMedicamentos(Long codigo, String paciente, String centrodeCusto, String entradaMedicamento,
			LocalDate dataSaida, Integer quantidade, BigDecimal valorUnitario) {
		this.codigo = codigo;
		this.paciente = paciente;
		this.centrodeCusto = centrodeCusto;
		this.entradaMedicamento = entradaMedicamento;
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

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public String getCentrodeCusto() {
		return centrodeCusto;
	}

	public void setCentrodeCusto(String centrodeCusto) {
		this.centrodeCusto = centrodeCusto;
	}

	public String getEntradaMedicamento() {
		return entradaMedicamento;
	}

	public void setEntradaMedicamento(String entradaMedicamento) {
		this.entradaMedicamento = entradaMedicamento;
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