@Configuration
public class DatabaseConfig {

	@Bean(name = "dataSourceProveedores")
	@ConfigurationProperties(prefix = "spring.datasource.proveedores")
	public DataSource dataSourceProveedores() {
	    return DataSourceBuilder.create().build();
	}

	@Bean(name = "jdbcTemplateProveedores")
	public JdbcTemplate jdbcTemplateProveedores(@Qualifier("dataSourceProveedores") DataSource ds) {
	    return new JdbcTemplate(ds);
	}
}
