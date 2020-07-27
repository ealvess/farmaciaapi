package com.farmacia.farmaciaapi.repository.projection;

public class ResumoFornecedores {

	private Long codigo;
	private String nomeFantasia;
	private String cnpj;
	private String telefone;
	private String telefone2;
	private String telefone3;
	private String email;

	public ResumoFornecedores(Long codigo, String nomeFantasia, String cnpj, String telefone, String telefone2,
			String telefone3, String email) {
		super();
		this.codigo = codigo;
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.telefone2 = telefone2;
		this.telefone3 = telefone3;
		this.email = email;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
