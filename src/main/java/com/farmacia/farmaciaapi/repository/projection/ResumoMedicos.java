package com.farmacia.farmaciaapi.repository.projection;

public class ResumoMedicos {

	private Long codigo;
	private String nome;
	private String crm;
	private String email;
	private String telefone;
	private String celular;
	private Boolean ativo;

	public ResumoMedicos(Long codigo, String nome, String crm, String email, String telefone, String celular,
			Boolean ativo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.crm = crm;
		this.email = email;
		this.telefone = telefone;
		this.celular = celular;
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

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
