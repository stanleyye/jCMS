package jcms.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	Datasource dataSource;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // CRSF protection needs to be disabled to handle the JWT cookies
			.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/register").access("hasRole(['admin', 'owner'])")
				.antMatchers("/admin/**").access("hasAnyRole(['author', 'admin', 'owner'])")//.authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				//.defaultSuccessUrl("/admin")
				//.failureUrl("/error")
				.and()
			.logout()
				.logoutSuccessUrl("/")
				.and()
			//.exceptionHandling()
			//	.accessDeniedPage("/access-denied")
			//	.and()
			// Filter /login endpoint requests
			.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
				UsernamePasswordAuthenticationFilter.class)
			// Filter other requests to check JWT
			.addFilterBefore(new JWTAuthenticationFilter(),
				UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication()
		// 	.withUser("admin")
		// 	.password("password")
		// 	.roles("ADMIN");

		auth
			.jdbcAuthentication().dataSource(dataSource).
			.usersByUsernameQuery(getUserQuery())
			.authoritiesByUsernameQuery(getAuthoriesQuery());
	}

	private String getAuthoriesQuery() {
		return "";
	}

	/**
	 * mySQL Query string to get user details.
	 *
	 * Note: spaces do matter
	 */
	private String getUserQuery() {
		return
			"SELECT username, password " +
			"FROM user " +
			"WHERE username = ?";
	}
}
