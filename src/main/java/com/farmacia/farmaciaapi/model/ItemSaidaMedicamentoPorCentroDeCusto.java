package com.farmacia.farmaciaapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "saida_medicamento_por_centro_de_custo")
public class ItemSaidaMedicamentoPorCentroDeCusto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne
	@JoinColumn(name = "codigo_centro_de_custo")
	private CentroDeCusto centrodeCusto;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_entrada_medicamento")
	private EntradaMedicamento entradaMedicamento;

	@NotNull
	@Column(name = "data_saida")
	private LocalDate dataSaida;

	@NotNull
	private BigDecimal quantidade;
	private BigDecimal valorUnitario;

	private BigDecimal total;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public CentroDeCusto getCentrodeCusto() {
		return centrodeCusto;
	}

	public void setCentrodeCusto(CentroDeCusto centrodeCusto) {
		this.centrodeCusto = centrodeCusto;
	}

	public EntradaMedicamento getEntradaMedicamento() {
		return entradaMedicamento;
	}

	public void setEntradaMedicamento(EntradaMedicamento entradaMedicamento) {
		this.entradaMedicamento = entradaMedicamento;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemSaidaMedicamentoPorCentroDeCusto other = (ItemSaidaMedicamentoPorCentroDeCusto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
