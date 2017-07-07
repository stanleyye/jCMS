package jcms.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Collections;

public class TokenService {
	public static final int EXPIRATION_TIME = 604800; // 7 days (604800 seconds)
	public static final String JWT_COOKIE_NAME = "jCMSCookie";

	// TODO: Signing key. Usually the key would be read from your application configuration instead.
	public static final Key SIGNING_KEY = MacProvider.generateKey();

	/*
	 * Adds an authentication cookie encoded with a JWT in the response header
	 *
	 * @param response The response header
	 * @param jwtSubject The JWT subject string to be encoded for the JWT
	 */
	public static void addJWTAuthentication(HttpServletResponse response, String jwtSubject) {
		response.addCookie(createJwtCookie(jwtSubject));
	}

	/*
	 * Create and return a cookie with a signed Json Web Token
	 *
	 * Note: Storing JWT on the client side in localStorage/sessionStorage makes it vulnerable to XSS.
	 * Normally, a cookie's state needs to be stored on the server but with JWT, it encapsulates
	 * everything needed for the request. Cookies are vulnerable to CSRF but that threat can be
	 * greatly reduced with token patterns or a framework's CSRF protection mechanisms
	 *
	 * @param jwtSubject The JSON Web token's subject (to be encoded)
	 * @return a cookie with an encoded JSON Web token
	 */
	public static Cookie createJwtCookie(String jwtSubject) {
		// TODO: Set secure / domain / http only parameters for the cookie
		// Sign the JWT using SHA-512 and compact it to a String form
		String compactJws = Jwts.builder()
			.setSubject(jwtSubject)
			.signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
			.compact();

		final Cookie jwtCookie = new Cookie(JWT_COOKIE_NAME, compactJws);
		jwtCookie.setMaxAge(EXPIRATION_TIME);
		return jwtCookie;
	}

	/*
	 * Returns a new token containing the token subject from the JWT cookie
	 *
	 * @param request The HTTP request
	 * @return an 'Authentication' token for simple representation of username and password
	 */
	public static Authentication getJWTAuthentication(HttpServletRequest request) {
		String tokenSubject = getJwtCookieSubject(request.getCookies());

		if (tokenSubject != null) {
			return new UsernamePasswordAuthenticationToken(tokenSubject, null, Collections.emptyList());
		}
		return null;
	}

	/*
	 * Gets the JSON Web token subject from the cookie
	 *
	 * @param cookies An array of cookies
	 * @param subjectToMatch The JSON Web token subject to match
	 * @returns a boolean indicating whether or not there is a cookie that matches the correct
	 *  JSON Web Token credentials
	 */
	public static String getJwtCookieSubject(Cookie[] cookies) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(JWT_COOKIE_NAME)) {
				String jwtSubject = Jwts.parser()
					.setSigningKey(SIGNING_KEY)
					.parseClaimsJws(cookie.getValue())
					.getBody()
					.getSubject();
				return jwtSubject;
			}
		}
		return null;
	}
}
