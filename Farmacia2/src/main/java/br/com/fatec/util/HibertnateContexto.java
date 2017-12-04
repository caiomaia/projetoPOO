package br.com.fatec.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.fatec.farmacia.HibernateUtil;

public class HibertnateContexto implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		HibernateUtil.getFabricaDeSessoes().close();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		HibernateUtil.getFabricaDeSessoes();
		
	}

}
