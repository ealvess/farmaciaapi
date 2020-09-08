package com.farmacia.farmaciaapi.repository.projection;

public class ResumoUsuario {

	private Long codigo;
	private String nome;
	private String tipo;
	private String email;
	private Boolean ativo;

	public ResumoUsuario(Long codigo, String nome, String tipo, String email, Boolean ativo) {
		this.codigo = codigo;
		this.nome = nome;
		this.tipo = tipo;
		this.email = email;
		this.ativo = ativo;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
