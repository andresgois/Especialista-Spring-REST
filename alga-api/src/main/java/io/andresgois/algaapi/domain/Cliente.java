package io.andresgois.algaapi.domain;

public class Cliente {

	private String nome;
	private String email;
	private String telefone;
	private boolean ativo = false;

	public Cliente(String nome, String email, String telefone) {
		this.email = email;
		this.nome = nome;
		this.telefone = telefone;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void ativar(){
		this.ativo = true;
	}
}
