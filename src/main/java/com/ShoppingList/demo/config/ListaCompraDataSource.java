package com.ShoppingList.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.provisioning.JdbcUserDetailsManager;



@Configuration
public class ListaCompraDataSource {
	
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager (@Qualifier("db1") DataSource ds){
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(ds);
		
		return jdbcUserDetailsManager;
		
	}
	
	  
	@Bean(name="db1")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource getDBTienda(){
		
		return DataSourceBuilder.create().build();
		
	}
	
	@Bean(name="jdbcTemplateDB1")
	public JdbcTemplate getJdbcTemplateDB1(@Qualifier("db1") DataSource ds){
		
		return new JdbcTemplate(ds);
	}
	
	
	@Bean(name="db2")
	@ConfigurationProperties(prefix = "spring.segunda.db")
	public DataSource getDBProveedores(){
		
		return DataSourceBuilder.create().build();
		
	}
	
	@Bean(name="jdbcTemplateDB2")
	public JdbcTemplate getJdbcTemplateDB2(@Qualifier("db2") DataSource ds){
		
		return new JdbcTemplate(ds);
	}
	


}