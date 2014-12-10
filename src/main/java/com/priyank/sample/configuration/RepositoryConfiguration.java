package com.priyank.sample.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass=true)
public class RepositoryConfiguration {

	@Value("${jdbc.driverClassName}")
	private String driverClassName;

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.username}")
	private String userName;

	@Value("${jdbc.password}")
	private String password;

	@Value("${hibernate.dialect}")
	private String dialect;

	@Value("${hibernate.show_sql}")
	private String showSql;

	@Value("${hibernate.current_session_context_class}")
	private String currentSessionContextClass;

	@Value("${hibernate.hbm2ddl.auto}")
	private String hbm2Dll;

	@Value("${hibernate.generate_statistics}")
	private String generateStatistics;

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	public Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.show_sql", showSql);
		properties.put("hibernate.hbm2ddl.auto", hbm2Dll);
//		properties.put("hibernate.current_session_context_class",
//				currentSessionContextClass);
		return properties;
	}

	@Bean
	public AnnotationSessionFactoryBean getSessionFactory() {
		AnnotationSessionFactoryBean sessionFactoryBean = new AnnotationSessionFactoryBean();
		sessionFactoryBean
				.setPackagesToScan(new String[] { "com.priyank.sample.domain" });
		sessionFactoryBean.setHibernateProperties(getHibernateProperties());
		sessionFactoryBean.setDataSource(getDataSource());
		return sessionFactoryBean;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager getHibernateTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}

	@Bean
	@Autowired
	public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory) {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(
				sessionFactory);
		return hibernateTemplate;
	}
}
