package br.com.fatec.farmacia.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fatec.farmacia.dao.ClienteDAO;
import br.com.fatec.farmacia.dao.EstoqueDAO;
import br.com.fatec.farmacia.dao.FuncionarioDAO;
import br.com.fatec.farmacia.dao.ItemVendaDAO;
import br.com.fatec.farmacia.dao.MedicamentoDAO;
import br.com.fatec.farmacia.dao.VendaDAO;
import br.com.fatec.farmacia.domain.Cliente;
import br.com.fatec.farmacia.domain.Estoque;
import br.com.fatec.farmacia.domain.Funcionario;
import br.com.fatec.farmacia.domain.ItemVenda;
import br.com.fatec.farmacia.domain.Medicamento;
import br.com.fatec.farmacia.domain.Venda;


@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class VendaBean implements Serializable{
	private Venda venda;
	ItemVenda itemVenda;
	
	private List<Medicamento> produtos;
	private List<ItemVenda> itensVenda;
	private List<Funcionario> funcionarios;
	private List<Cliente> clientes;
	private List<Venda> vendas;
	
	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public ItemVenda getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Medicamento> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Medicamento> produtos) {
		this.produtos = produtos;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	@PostConstruct
	public void novo() {
		try {
			venda = new Venda();
			venda.setValorTotal(new BigDecimal("0.00"));
			
			MedicamentoDAO produtoDAO = new MedicamentoDAO();
			produtos = produtoDAO.listar();
			itensVenda = new ArrayList<>();	
			
			VendaDAO vendaDAO=new VendaDAO();
			vendas=vendaDAO.listar();
			
			
			
			
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Medicamento produto = (Medicamento) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getMedicamento().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setSubtotal(produto.getPrecoUnitario());
			itemVenda.setMedicamento(produto);
			itemVenda.setQuantidade(1);

			itensVenda.add(itemVenda);
		} else {
			ItemVenda itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
			itemVenda.setSubtotal(produto.getPrecoUnitario().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}
		
		calcular();
	}

	public void remover(ActionEvent evento) {
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");
		
		int achou = -1;
		for(int posicao = 0; posicao < itensVenda.size(); posicao++){
			if(itensVenda.get(posicao).getMedicamento().equals(itemVenda.getMedicamento())){
				achou = posicao;
			}
		}
		
		if(achou > -1){
			itensVenda.remove(achou);
		}
		
		calcular();
	}
	public void diminuir(ActionEvent evento){
		try {
			Medicamento produto=(Medicamento) evento.getComponent().getAttributes().get("itemSelecionado");
			itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() - 1 + ""));
			itemVenda.setSubtotal(produto.getPrecoUnitario().subtract(new BigDecimal(1)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		calcular();
	}
	
	public void calcular(){
		venda.setValorTotal(new BigDecimal("0.00"));
		
		for(int posicao = 0; posicao < itensVenda.size(); posicao++){
			ItemVenda itemVenda = itensVenda.get(posicao);
			venda.setValorTotal(venda.getValorTotal().add(itemVenda.getSubtotal()));
		}
	}
	public void finalizar(){
		try {
			venda.setDataHora(new Date());
			FuncionarioDAO funcionarioDAO=new FuncionarioDAO();
			funcionarios=funcionarioDAO.listar("nome");
			ClienteDAO clienteDAO= new ClienteDAO();
			clientes=clienteDAO.listar("nome");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void salvar(){
		try {
			if(venda.getValorTotal().signum() == 0){
				Messages.addGlobalError("Informe pelo menos um item para a venda");
				return;
			}
			
			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.salvar(venda, itensVenda);
			novo();
			
			/*(
			Estoque estoque= new Estoque();
			List<Estoque> estoques;
			EstoqueDAO estDAO= new EstoqueDAO();
			estoques=estDAO.listar();
			
			
			for(int i=0;i<itensVenda.size();i++){
				if(estoques.get(i).getMedicamento().getNome().equals(itemVenda.getMedicamento().getNome())){
					
					estoque.setQuantidade(estoque.getQuantidade()-itemVenda.getQuantidade());
				}
			}*/
			
			Messages.addGlobalInfo("Venda realizada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a venda");
			erro.printStackTrace();
		}
	}
}
