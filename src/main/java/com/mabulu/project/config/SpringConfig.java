package com.mabulu.project.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// TODO: Auto-generated Javadoc
/**
 * The Class SpringConfig.
 */
@Configuration
@EnableTransactionManagement
//@ComponentScan("com.mabulu.*")
//@Import({ AuthorizationServerConfiguration.class, OAuth2SecurityConfiguration.class })
@EnableWebMvc
@PropertySource("classpath:application-dev.properties")
@ComponentScan(basePackages = { "com.mabulu.*" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
@EnableResourceServer
public class SpringConfig extends ResourceServerConfigurerAdapter {

	/** The jdbc url. */
	@Value("${jdbc.url}")
	private String jdbcUrl;

	/** The jdbc username. */
	@Value("${jdbc.username}")
	private String jdbcUsername;

	/** The driver. */
	@Value("${jdbc.driver}")
	private String driver;

	/** The password. */
	@Value("${jdbc.password}")
	private String password;

	/**
	 * My interceptor.
	 *
	 * @return the mapped interceptor
	 *//*
		 * @Bean public MappedInterceptor myInterceptor() { String[] urlPatterns = {
		 * "/v4/*" }; return new MappedInterceptor(urlPatterns, new LoginInterceptor());
		 * }
		 */

	/**
	 * Data source.
	 *
	 * @return the data source
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(jdbcUsername);
		dataSource.setPassword(password);
		return dataSource;
	}

	private static final String RESOURCE_ID = "my_rest_api";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().and().anonymous().disable()
				.requestMatchers().antMatchers("/v3/**").and().authorizeRequests().antMatchers("/v4/**")
				.access("hasRole('ADMIN')").and().exceptionHandling()
				.accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

}
