package br.com.fatec.farmacia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity

public class Funcionario extends GenericDomain{
	
	@Column(length=30, nullable=false)
	String nome;
	
	@Column(length=30, nullable=false)
	String cargo;
	

	@Column(length=10, nullable=false)
	String senha;
	
	@Column(nullable=false)
	float salario;
	
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}

}
