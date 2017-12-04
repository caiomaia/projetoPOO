package br.com.fatec.farmacia.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fatec.farmacia.dao.EstoqueDAO;
import br.com.fatec.farmacia.dao.MedicamentoDAO;
import br.com.fatec.farmacia.domain.Estoque;
import br.com.fatec.farmacia.domain.Medicamento;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstoqueBean implements Serializable{
	private List<Estoque> estoques;
	private Estoque estoque;
	private List<Medicamento> medicamentos;
	

	
	public List<Estoque> getEstoques() {
		return estoques;
	}
	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	@PostConstruct
	public void listar(){
		try{
			EstoqueDAO estoqueDAO=new EstoqueDAO();
			estoques = estoqueDAO.listar();
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar o estoque");
			erro.printStackTrace();
		}
	}
	public void novo(){
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Messages.addFlashGlobalError("Erro ao gerar cidade");
		}
		
		estoque=new Estoque();
		MedicamentoDAO medicamentoDAO=new MedicamentoDAO();
		medicamentos=medicamentoDAO.listar();
	}
	
	public void salvar(){
		
		try {
			EstoqueDAO estoqueDAO=new EstoqueDAO();
			estoqueDAO.merge(estoque);
			estoque=new Estoque();
			estoques=estoqueDAO.listar();
			
			MedicamentoDAO medicamentoDAO=new MedicamentoDAO();
			medicamentos=medicamentoDAO.listar();
			Messages.addGlobalInfo("Item salvo");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			Messages.addGlobalInfo("Erro");
			e.printStackTrace();
		}
	}
	public void excluir(ActionEvent evento){
		try {
			estoque= (Estoque) evento.getComponent().getAttributes().get("estoqueSelecionado");
			EstoqueDAO estoqueDAO=new EstoqueDAO();
			estoqueDAO.excluir(estoque);
			estoques=estoqueDAO.listar();
			Messages.addGlobalInfo("Item editado");
		} catch (Exception e) {
			// TODO: handle exception
			Messages.addGlobalError("Erro");
			e.printStackTrace();
		}
		
	}
	public void editar(ActionEvent evento){
		try {
			estoque= (Estoque) evento.getComponent().getAttributes().get("estoqueSelecionado");
			MedicamentoDAO medicamentoDAO=new MedicamentoDAO();
			medicamentos=medicamentoDAO.listar();
			
		} catch (Exception e) {
			// TODO: handle exception
			Messages.addGlobalError("Erro");
			e.printStackTrace();
		}
	}
	
	public void adicionar(ActionEvent evento){
		try {
			estoque= (Estoque) evento.getComponent().getAttributes().get("estoqueSelecionado");
			estoque.setQuantidade(estoque.getQuantidade()+1);
			//estoques=estoqueDAO.listar();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	public void remover(ActionEvent evento){
		
		try {
			estoque= (Estoque) evento.getComponent().getAttributes().get("estoqueSelecionado");
			estoque.setQuantidade(estoque.getQuantidade()-1);
			//estoques=estoqueDAO.listar();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		
	}
