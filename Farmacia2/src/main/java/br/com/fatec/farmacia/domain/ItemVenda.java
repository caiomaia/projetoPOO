package br.com.fatec.farmacia.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity

public class ItemVenda extends GenericDomain{
	
	@Column(nullable=false)
	int quantidade;
	
	@Column(nullable=false)
	BigDecimal subtotal;
	
	@JoinColumn(nullable=false)
	@ManyToOne
	Medicamento medicamento;
	
	@JoinColumn(nullable=false)
	@ManyToOne
	Venda venda;
	
	
	public Medicamento getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	

}
