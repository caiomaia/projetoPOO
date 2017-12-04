package br.com.fatec.farmacia.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import br.com.fatec.farmacia.HibernateUtil;

public class GenericDAO  <Entidades>{
	private Class<Entidades> classe;
	
	public GenericDAO() {
this.classe=	(Class<Entidades>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

}

	
	public void salvar(Entidades entidade){
		
		Session sessao= HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao=null;
		
		try{
			transacao=sessao.beginTransaction();
			sessao.save(entidade);
		
			transacao.commit();
		}catch(RuntimeException erro){
			if(transacao !=null){
				transacao.rollback();
			}
			throw erro;
		}finally {
			sessao.close();
		}
		
	}
	@SuppressWarnings("unchecked")
	public List<Entidades> listar(){
		
		Session sessao= HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta=sessao.createCriteria(classe);
			List<Entidades> resultado=consulta.list();
			return resultado;

		}catch(RuntimeException erro){
			throw erro;
		}finally {
			sessao.close();
		}
		
		
		
		
		
	}
	@SuppressWarnings("unchecked")
	public List<Entidades> listar(String campoOrdenacao){
		
		Session sessao= HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta=sessao.createCriteria(classe);
			consulta.addOrder(Order.asc(campoOrdenacao));
			List<Entidades> resultado=consulta.list();
			return resultado;

		}catch(RuntimeException erro){
			throw erro;
		}finally {
			sessao.close();
		}
		
		
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	public Entidades buscar(Long codigo){
		
		Session sessao= HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta=sessao.createCriteria(classe);
			consulta.add(Restrictions.idEq(codigo));
			
			Entidades resultado=(Entidades)consulta.uniqueResult();
			return resultado;

		}catch(RuntimeException erro){
			throw erro;
		}finally {
			sessao.close();
		}
		
		
		
		
		
	}
public void excluir(Entidades entidade){
		
		Session sessao= HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao=null;
		
		try{
			transacao=sessao.beginTransaction();
			sessao.delete(entidade);
		
			transacao.commit();
		}catch(RuntimeException erro){
			if(transacao !=null){
				transacao.rollback();
			}
			throw erro;
		}finally {
			sessao.close();
		}
		
	}
public void editar(Entidades entidade){
	
	Session sessao= HibernateUtil.getFabricaDeSessoes().openSession();
	Transaction transacao=null;
	
	try{
		transacao=sessao.beginTransaction();
		sessao.update(entidade);
	
		transacao.commit();
	}catch(RuntimeException erro){
		if(transacao !=null){
			transacao.rollback();
		}
		throw erro;
	}finally {
		sessao.close();
	}
	
}
public void merge (Entidades entidade){
	
	Session sessao= HibernateUtil.getFabricaDeSessoes().openSession();
	Transaction transacao=null;
	
	try{
		transacao=sessao.beginTransaction();
		sessao.merge(entidade);
	
		transacao.commit();
	}catch(RuntimeException erro){
		if(transacao !=null){
			transacao.rollback();
		}
		throw erro;
	}finally {
		sessao.close();
	}
	
}
	

}
