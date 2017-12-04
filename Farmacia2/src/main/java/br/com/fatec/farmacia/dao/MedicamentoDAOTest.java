package br.com.fatec.farmacia.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Ignore;
import org.junit.Test;

import br.com.fatec.farmacia.domain.Medicamento;

public class MedicamentoDAOTest {
	@Test
	
	public void salvar(){
	
		Medicamento medicamento = new Medicamento();
		medicamento.setNome("Cataflan 50mg com 20 Comprimidos");
		medicamento.setUnidade("Comprimido");
		medicamento.setPrecoUnitario(new BigDecimal("13.70"));
		medicamento.setFabricante("Bayer");
		try {
			medicamento.setData(new SimpleDateFormat("dd/MM/yyyy").parse("09/06/2015"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
		medicamentoDAO.salvar(medicamento);
		
		System.out.println("Produto salvo com sucesso");
	}

}
