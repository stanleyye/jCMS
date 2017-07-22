package jcms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jcms.config.SpringAppContext;
import jcms.service.UserRoleService;
import jcms.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	private boolean continueChainBeforeSuccessfulAuthentication = false;
	private SessionAuthenticationStrategy sessionStrategy = new NullAuthenticatedSessionStrategy();

	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
		throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;

		boolean isGETRequest = request.getMethod().equalsIgnoreCase("GET");

		// If the request doesn't require any authentication or is a HTTP GET request, then do nothing
		if (!this.requiresAuthentication(request, response) || isGETRequest) {
			chain.doFilter(request, response);
		} else {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Request is to process authentication");
			}

			Authentication authResult;
			try {
				authResult = this.attemptAuthentication(request, response);
				if (authResult == null) {
					return;
				}

				this.sessionStrategy.onAuthentication(authResult, request, response);
			} catch (InternalAuthenticationServiceException var8) {
				this.logger.error("An internal error occurred while trying to authenticate the user.", var8);
				this.unsuccessfulAuthentication(request, response, var8);
				return;
			} catch (AuthenticationException var9) {
				this.unsuccessfulAuthentication(request, response, var9);
				return;
			}

			if (this.continueChainBeforeSuccessfulAuthentication) {
				chain.doFilter(request, response);
			}

			this.successfulAuthentication(request, response, chain, authResult);
		}
	}

	@Override
	public Authentication attemptAuthentication(
		HttpServletRequest request,
		HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		// Map to JSON construct from the request into a LoginCredentials object

		LoginCredentials login = new ObjectMapper().readValue(request.getInputStream(), LoginCredentials.class);

		// Return a fully populated Authentication object (in this case, the UserPassAuthToken) if authentication
		// is successful
		return getAuthenticationManager().authenticate(
			new UsernamePasswordAuthenticationToken(
				login.getUsername(),
				login.getPassword(),
				Collections.emptyList()
			)
		);
	}

	@Override
	protected void successfulAuthentication(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain chain,
		Authentication authResult) throws IOException, ServletException {
		// TODO: redirect to /admin
		String loginUsername = authResult.getName();

		// Get the userRoleService and userService bean
		ApplicationContext applicationContext = SpringAppContext.getApplicationContext();
		UserService userService = (UserService)applicationContext.getBean("userService");
		UserRoleService userRoleService = (UserRoleService)applicationContext.getBean("userRoleService");

		try {
			// Find the user's ID by their username
			final int userID = userService.findByUsername(loginUsername).getId();
			final String role = userRoleService.findByForeignKeyUserUsername(loginUsername).getRole().getRoleName();

			// Create a new payload consisting of the user's username and their role level
			JWTPayload jwtPayload = new JWTPayload(
				loginUsername,
				role
			);

			TokenService.addJWTAuthentication(response, jwtPayload);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
