package br.com.fatec.farmacia.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fatec.farmacia.dao.CidadeDAO;
import br.com.fatec.farmacia.dao.ClienteDAO;
import br.com.fatec.farmacia.dao.EstadoDAO;
import br.com.fatec.farmacia.domain.Cidade;
import br.com.fatec.farmacia.domain.Cliente;
import br.com.fatec.farmacia.domain.Estado;

@ManagedBean
@ViewScoped
public class ClienteBean {
	
	private Cliente cliente;
	List <Cliente> clientes;
	List<Cidade> cidades;
	private List<Estado> estados;
	private Estado estado;
	
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
	public List<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public void novo(){
		cliente=new Cliente();
//		CidadeDAO cidadeDAO=new CidadeDAO();
//		cidades=cidadeDAO.listar("nome");
		EstadoDAO ed=new EstadoDAO();
		estados=ed.listar("nome");
		cidades=new ArrayList<>();
	}
	@PostConstruct
	public void listar(){
		
		try {
			ClienteDAO clienteDAO=new ClienteDAO();
			clientes=clienteDAO.listar();
			
		} catch (Exception e) {
			// TODO: handle exception
			Messages.addGlobalError("Erro ao listar");
		}
		
		
	}
	public void salvar(){
		try {
			ClienteDAO clienteDAO=new ClienteDAO();
			clienteDAO.merge(cliente);
			clientes=clienteDAO.listar();
			Messages.addGlobalInfo("Item salvo");
			CidadeDAO cidadeDAO=new CidadeDAO();
			cidades=cidadeDAO.listar("nome");
			
			novo();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			Messages.addGlobalError("Erro ao salvar");
			e.printStackTrace();
		}
		
	}
	public void editar(ActionEvent evento){
		cliente= (Cliente) evento.getComponent().getAttributes().get("clienteSelelcionado");
		CidadeDAO cidadeDAO=new CidadeDAO();
		cidades=cidadeDAO.listar("nome");
	
	}
	public void excluir(ActionEvent evento){
		try {
			cliente= (Cliente) evento.getComponent().getAttributes().get("clienteSelelcionado");
			ClienteDAO clienteDAO=new ClienteDAO();
			clienteDAO.excluir(cliente);
			clientes=clienteDAO.listar();
			Messages.addGlobalInfo("Item excluido");
			
		} catch (Exception e) {
			// TODO: handle exception
			Messages.addGlobalError("Erro ao excluir");
			e.printStackTrace();
		}
	}
	public void popular(){
		
		try {
			if (estado != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
			} else {
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
			erro.printStackTrace();
		}
	}

}
