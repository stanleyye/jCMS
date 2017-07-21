package jcms.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyDaoAuthenticationProvider myDaoAuthenticationProvider;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getDaoAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // CRSF protection needs to be disabled to handle the JWT cookies
			.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/api/**").authenticated()
				.antMatchers("/admin/**").authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				//.defaultSuccessUrl("/admin")
				.and()
			.logout()
				.logoutSuccessUrl("/")
				.and()
			// Filter /login endpoint requests
			.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
				UsernamePasswordAuthenticationFilter.class)
			// Filter all other requests to check JWT
			.addFilterBefore(new JWTAuthenticationFilter(),
				UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	private DaoAuthenticationProvider getDaoAuthenticationProvider() {
		myDaoAuthenticationProvider.setUserDetailsService(userDetailsService);
		myDaoAuthenticationProvider.setPasswordEncoder(UserServiceImpl.PASSWORD_ENCODER);
		return myDaoAuthenticationProvider;
	}
}
