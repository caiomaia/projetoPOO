package br.com.fatec.farmacia.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fatec.farmacia.dao.CidadeDAO;
import br.com.fatec.farmacia.dao.EstadoDAO;
import br.com.fatec.farmacia.domain.Cidade;
import br.com.fatec.farmacia.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable{
	private List<Cidade> cidades;
	private Cidade cidade;
	private List<Estado> estados;
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}
	
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	@PostConstruct
	public void listar(){
		try{
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as cidades");
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
		
		cidade=new Cidade();
		EstadoDAO estadoDAO=new EstadoDAO();
		estados=estadoDAO.listar("nome");
	}
	
	public void salvar(){
		
		try {
			CidadeDAO cidadeDAO=new CidadeDAO();
			cidadeDAO.merge(cidade);
			cidade=new Cidade();
			cidades=cidadeDAO.listar();
			
			EstadoDAO estadoDAO=new EstadoDAO();
			estados=estadoDAO.listar("nome");
			Messages.addGlobalInfo("Item salvo");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			Messages.addGlobalInfo("Erro");
			e.printStackTrace();
		}
	}
	public void excluir(ActionEvent evento){
		try {
			cidade= (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
			CidadeDAO cidadeDAO=new CidadeDAO();
			cidadeDAO.excluir(cidade);
			cidades=cidadeDAO.listar();
			Messages.addGlobalInfo("Item editado");
		} catch (Exception e) {
			// TODO: handle exception
			Messages.addGlobalError("Erro");
			e.printStackTrace();
		}
		
	}
	public void editar(ActionEvent evento){
		try {
			cidade= (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
			EstadoDAO estadoDAO=new EstadoDAO();
			estados=estadoDAO.listar("nome");
			
		} catch (Exception e) {
			// TODO: handle exception
			Messages.addGlobalError("Erro");
			e.printStackTrace();
		}
	}
		
	}
