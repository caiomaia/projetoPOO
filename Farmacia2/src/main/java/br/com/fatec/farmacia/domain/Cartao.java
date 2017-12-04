package br.com.fatec.farmacia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Cartao extends Pagamento{
	
	@Column(length=10, nullable=false)
	String bandeira;
	
	@Column(length=10, nullable=false)
	String tipo;
	
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	

}
