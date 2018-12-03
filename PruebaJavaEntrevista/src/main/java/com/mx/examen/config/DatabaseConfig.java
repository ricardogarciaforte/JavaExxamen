package com.mx.examen.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * This class performs the configuration for access to the system database.
 * 
 * @author Ricardo Garcia Forte
 * @version 1.0
 * @created 2018-11-30
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.mx.examen.repository")
@EnableTransactionManagement
@ComponentScan("com.mx.examen")
public class DatabaseConfig {

	@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		//configures the database driver
		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		//indicates the location of the database and its name
		dataSource.setUrl("jdbc:hsqldb:file:C:\\hsqldb\\security-db");
		dataSource.setUsername("sa");
		dataSource.setPassword("");		
		createDatabasePopulator(dataSource);
		
		return dataSource;
	}

	/**
	 * Creates the EntityManagerFactory for JPA.
	 */
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.mx.examen.entity");
		factory.setDataSource(dataSource());
		factory.setJpaDialect(new HibernateJpaDialect()); 
		factory.afterPropertiesSet();
		
		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		
		return txManager;
	}
	

	public void createDatabasePopulator(DataSource dataSource) {
		
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        //executes the scripts contained in the file passed by parameter in this command
        databasePopulator.addScript(new ClassPathResource("data.sql"));
        
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
    }

}
