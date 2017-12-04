package br.com.fatec.farmacia.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fatec.farmacia.dao.FuncionarioDAO;
import br.com.fatec.farmacia.domain.Funcionario;

@ManagedBean
@ViewScoped

public class FuncionarioBean {

	Funcionario funcionario = new Funcionario();
	List<Funcionario> funcionarios;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@PostConstruct
	public void listar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

		} catch (Exception e) {
			// TODO: handle exception
			Messages.addGlobalError("Erro ao listar funcionarios");
		}
	}

	public void novo() {
		funcionario = new Funcionario();
	}

	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.merge(funcionario);
			funcionarios = funcionarioDAO.listar();
			novo();
			Messages.addGlobalInfo("Item salvo");

		} catch (Exception e) {
			// TODO: handle exception
			Messages.addGlobalError("Erro ao salvar");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

	}

	public void excluir(ActionEvent evento) {

		try {	
			FuncionarioDAO funcionarioDAO= new FuncionarioDAO();
			funcionario= (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
			funcionarioDAO.excluir(funcionario);
			funcionarios=funcionarioDAO.listar();
			Messages.addGlobalInfo("Item excluído");
			

		} catch (Exception e) {
			Messages.addGlobalError("Ocorreu um erro ao excluir item");
			e.printStackTrace();
			// TODO: handle exception
		}

	}

}
