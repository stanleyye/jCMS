package jcms.security;

import io.jsonwebtoken.SignatureException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.security.core.Authentication;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
			filterChain.doFilter(request, response);
		} catch (SignatureException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());

			// Delete the JWT cookie because the JWT's signature is invalid
			TokenService.deleteJwtCookie((HttpServletRequest)request, (HttpServletResponse)response);
			((HttpServletResponse) response).sendRedirect("/login");
		}
	}
}
