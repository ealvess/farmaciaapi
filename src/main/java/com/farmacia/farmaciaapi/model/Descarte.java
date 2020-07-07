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
@Table(name = "descarte")
public class Descarte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne
	@JoinColumn(name = "codigo_medicamento")
	private Medicamento medicamento;

	@ManyToOne
	@JoinColumn(name = "codigo_correlato")
	private Correlato correlato;

	@NotNull
	private String motivo;

	@NotNull
	@Column(name = "data_saida")
	private LocalDate dataSaida;

	@NotNull
	private Integer quantidade;

	private BigDecimal valorUnitario;

	private BigDecimal total;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Correlato getCorrelato() {
		return correlato;
	}

	public void setCorrelato(Correlato correlato) {
		this.correlato = correlato;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
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
		Descarte other = (Descarte) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
