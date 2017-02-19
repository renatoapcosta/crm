package org.pulem3t.crm.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

//@EnableTransactionManagement
public class HibernateConfiguration {
	
	
	@Bean
	public Properties hibernateProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		props.setProperty("hibernate.globally_quoted_identifiers", "true");
		
		return props;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource,Properties hibernateProperties) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[] { "org.pulem3t.crm.entry" });
		sessionFactory.setHibernateProperties(hibernateProperties);
		
		return sessionFactory;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
	public HibernateTransactionManager txManager(SessionFactory sessionFactory){
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

}
