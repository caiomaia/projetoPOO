package br.com.fatec.farmacia.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
public class Caixa extends GenericDomain{
	
	@Column(nullable=false)
	int qntNotasAb;
	
	@Column(nullable=false)
	int qntMoedasAb;
	
	@Column(nullable=false)
	float valorTotal;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	Date dataHoraAb;
	
	@JoinColumn(nullable=false)
	@OneToMany
	private List<Venda> vendas=new ArrayList<Venda>();
	
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	Date dataHoraFech;
	
	public int getQntNotasAb() {
		return qntNotasAb;
	}
	public void setQntNotasAb(int qntNotasAb) {
		this.qntNotasAb = qntNotasAb;
	}
	public int getQntMoedasAb() {
		return qntMoedasAb;
	}
	public void setQntMoedasAb(int qntMoedasAb) {
		this.qntMoedasAb = qntMoedasAb;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Date getDataHoraAb() {
		return dataHoraAb;
	}
	public void setDataHoraAb(Date dataHoraAb) {
		this.dataHoraAb = dataHoraAb;
	}
	public Date getDataHoraFech() {
		return dataHoraFech;
	}
	public void setDataHoraFech(Date dataHoraFech) {
		this.dataHoraFech = dataHoraFech;
	}
	
	
	

}
