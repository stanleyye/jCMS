package jcms.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.security.core.Authentication;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain filterChain) throws IOException, ServletException {
		try {
			// Get the token for the authentication request
			Authentication authentication = TokenService.getJWTAuthentication((HttpServletRequest)request);
			// Manually set the authentication token in the thread-local SecurityContext (managed by SecurityContextHolder)
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Error e) {
			response.sendRedirect("/login");
			return;
		}

		filterChain.doFilter(request, response);
	}
}
