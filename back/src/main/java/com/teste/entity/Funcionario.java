package com.teste.entity;

public class Funcionario {
	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private Long nis;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getNis() {
		return nis;
	}
	public void setNis(Long nis) {
		this.nis = nis;
	}
}