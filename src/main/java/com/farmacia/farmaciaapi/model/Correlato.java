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
@Table(name = "correlato")
public class Correlato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_grupo")
	private GrupoCorrelato codigo_grupo;

	@NotNull
	@Column(name = "data_entrada")
	private LocalDate dataEntrada;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_fornecedor")
	private Fornecedor fornecedor;

	@NotNull
	private String numeroNotaFiscal;

	@NotNull
	private String nomeCorrelato;

	@NotNull
	private String apresentacao;

	@NotNull
	@Column(name = "data_fabricacao")
	private LocalDate dataFabricacao;

	@NotNull
	@Column(name = "data_validade")
	private LocalDate dataValidade;

	@NotNull
	private Integer quantidade;

	@NotNull
	private BigDecimal valorUnitario;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public GrupoCorrelato getCodigo_grupo() {
		return codigo_grupo;
	}

	public void setCodigo_grupo(GrupoCorrelato codigo_grupo) {
		this.codigo_grupo = codigo_grupo;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public String getNomeCorrelato() {
		return nomeCorrelato;
	}

	public void setNomeCorrelato(String nomeCorrelato) {
		this.nomeCorrelato = nomeCorrelato;
	}

	public String getApresentacao() {
		return apresentacao;
	}

	public void setApresentacao(String apresentacao) {
		this.apresentacao = apresentacao;
	}

	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(LocalDate dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
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
		Correlato other = (Correlato) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
