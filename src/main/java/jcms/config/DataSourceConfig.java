package jcms.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Data source config.
 *
 * By default, Spring Boot already configures a data source for you if you specify the right values
 * in applications.properties. This config is here to do any extra config outside of the auto config
 * by Spring Boot.
 */

@Configuration
@Component
public class DataSourceConfig {

	private String driverClassName;
	private String url;
	private String username;
	private String password;

	/**
	 * Gets the Data Source.
	 *
	 * Note: By default, spring boot already configures the data source for you but this is needed
	 * @return the datasource (an implementation of the database wrapper)
	 */
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder
			.create()
			.driverClassName(driverClassName)
			.url(url)
			.username(username)
			.password(username)
			.build();
	}
}
