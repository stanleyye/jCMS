package jcms.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/admin/**")
				.authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/")
				.failureUrl("/error")
				.and()
			.logout()
				.logoutSuccessUrl("/")
				.and()
			.exceptionHandling()
				.accessDeniedPage("/access-denied")
				.and()
			// Filter /login endpoint requests
			.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
				UsernamePasswordAuthenticationFilter.class)
			// Filter other requests to check JWT
			.addFilterBefore(new JWTAuthenticationFilter(),
				UsernamePasswordAuthenticationFilter.class);
	}
}
