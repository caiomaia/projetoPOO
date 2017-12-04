package br.com.fatec.farmacia.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Medicamento extends GenericDomain {
	@Column(length=50,nullable=false)
	private String nome;
	
	@Column(length=50,nullable=false)
	private String fabricante;
	
	@Column(nullable=false)
	private BigDecimal PrecoUnitario;
	
	public BigDecimal getPrecoUnitario() {
		return PrecoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		PrecoUnitario = precoUnitario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column(length=20,nullable=false)
	private String unidade;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date data;
	
}
