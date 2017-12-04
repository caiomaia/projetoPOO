package br.com.fatec.faramacia;

import org.hibernate.Session;
import org.junit.Test;

import br.com.fatec.farmacia.HibernateUtil;

public class HibernatutilTest {
	
	@Test
	public void conectar(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}

}
