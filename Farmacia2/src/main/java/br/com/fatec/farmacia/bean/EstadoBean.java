package br.com.fatec.farmacia.bean;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fatec.farmacia.dao.EstadoDAO;
import br.com.fatec.farmacia.domain.Estado;

@ManagedBean
@ViewScoped
public class EstadoBean {

	private Estado estado;
	private List<Estado> estados;
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	@PostConstruct
	public void listar(){
		
		try {
			EstadoDAO estadoDAO=new EstadoDAO();
			estados=estadoDAO.listar();
			
			
		} catch (Exception e) {
			
			Messages.addGlobalError("Erro ao carregar lista");
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public void novo(){
		estado=new Estado();
	}

	public void salvar(){
		
		try {
		
		EstadoDAO estadoDAO= new EstadoDAO();
		estadoDAO.merge(estado);
		novo();
		estados=estadoDAO.listar();
		Messages.addGlobalInfo("Registro Salvo");
		
		
			
		} catch (Exception e) {
			Messages.addGlobalError("Ocorreu um erro salvar registro");
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	
	public void excluir(ActionEvent evento){
		
		try {
			EstadoDAO estadoDAO= new EstadoDAO();
			estado= (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
			estadoDAO.excluir(estado);
			estados=estadoDAO.listar();
			Messages.addGlobalInfo("Item excluído");
			
		} catch (Exception e) {
			Messages.addGlobalError("Ocorreu um erro ao excluir item");
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
	}
	public void editar(ActionEvent evento){
		estado=(Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
/*
		try {
			EstadoDAO estadoDAO=new EstadoDAO();
			estadoDAO.editar(estado);
			estados=estadoDAO.listar();
			
			
		} catch (Exception e) {
			
			Messages.addGlobalError("Erro ao editar item");
			e.printStackTrace();
			// TODO: handle exception
		}
	*/	
	}
	

}
