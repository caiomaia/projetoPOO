package br.com.fatec.util;

import org.hibernate.Session;
import org.junit.Test;

import br.com.fatec.farmacia.HibernateUtil;

public class HibernateUtilTest {
	@Test
	public static void main(String[] args) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}
