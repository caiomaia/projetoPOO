package br.com.fatec.farmacia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Pagamento extends GenericDomain{
	
	@Column(nullable=false)
	float valor;
	
	@Column(nullable=false)
	float desconto;
	
	
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public float getDesconto() {
		return desconto;
	}
	public void setDesconto(float desconto) {
		this.desconto = desconto;
	}
	

}
