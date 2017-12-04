package br.com.fatec.farmacia.bean;



import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fatec.farmacia.dao.MedicamentoDAO;
import br.com.fatec.farmacia.domain.Medicamento;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MedicamentoBean implements Serializable{
	
	private List <Medicamento> medicamentos;
	private Medicamento medicamento;
	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	public Medicamento getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}
	
	public void novo(){
		medicamento=new Medicamento();
		
	}
	
	@PostConstruct
	public void listar(){
		try {
			MedicamentoDAO medicamentoDAO=new MedicamentoDAO();
			medicamentos=medicamentoDAO.listar();
			
		} catch (Exception e) {
			// TODO: handle exception
			Messages.addGlobalError("Erro ao listar medicamentos");
		}
		
		
	}
	
	public void salvar(){
		try {
			MedicamentoDAO medicamentoDAO=new MedicamentoDAO();
			medicamentoDAO.merge(medicamento);
			medicamentos=medicamentoDAO.listar();
			novo();
			Messages.addGlobalInfo("Item salvo");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			Messages.addGlobalError("Erro ao salvar item");
			e.getMessage();
		}
	}
	public void editar(ActionEvent evento){
		medicamento=(Medicamento) evento.getComponent().getAttributes().get("medicamentoSelecionado");
		
	}
	public void excluir(ActionEvent evento){
		try {
			MedicamentoDAO medicamentoDAO=new MedicamentoDAO();
			medicamento=(Medicamento) evento.getComponent().getAttributes().get("medicamentoSelecionado");

			medicamentoDAO.excluir(medicamento);
			medicamentos=medicamentoDAO.listar();
			novo();
			Messages.addGlobalInfo("Item excluído");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			Messages.addGlobalError("Erro ao excluir");
			e.printStackTrace();
		}
	}
	
	
	
	

}
