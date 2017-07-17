package jcms.security;

import io.jsonwebtoken.Claims;
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
import java.util.Date;

public class TokenService {
	public static final int EXPIRATION_TIME = 604_800_000; // 7 days (604800000 milliseconds)
	public static final String JWT_COOKIE_NAME = "jCMSCookie";

	// TODO: Signing key. Usually the key would be read from your application configuration instead.
	// Every time server is reloaded, the signing key is changed. A SignatureException will be thrown
	// if the JWT browser cookie isn't deleted when server is reloaded since the signing keys are
	// different.
	public static final Key SIGNING_KEY = MacProvider.generateKey();

	/**
	 * Adds an authentication cookie encoded with a JWT in the response header
	 *
	 * @param response The response header
	 * @param jwtPayload The payload to be encoded for JWT
	 */
	public static void addJWTAuthentication(HttpServletResponse response, JWTPayload jwtPayload) {
		response.addCookie(createJwtCookie(jwtPayload));
	}

	/**
	 * Create and return a cookie with a signed Json Web Token
	 *
	 * Note: Storing JWT on the client side in localStorage/sessionStorage makes it vulnerable to XSS.
	 * Normally, a cookie's state needs to be stored on the server but with JWT, it encapsulates
	 * everything needed for the request. Cookies are vulnerable to CSRF but that threat can be
	 * greatly reduced with token patterns or a framework's CSRF protection mechanisms
	 *
	 * @param jwtPayload The payload of the JWT
	 * @return a cookie with an encoded JSON Web token
	 */
	public static Cookie createJwtCookie(JWTPayload jwtPayload) {
		// TODO: Set secure / domain / http only parameters for the cookie
		// Sign the JWT using SHA-512 and compact it to a String form
		Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
		String compactJws = Jwts.builder()
			.setSubject(jwtPayload.getUsername())
			.claim("roleLevel", jwtPayload.getRoleLevel())
			.setExpiration(expirationDate)
			.signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
			.compact();

		final Cookie jwtCookie = new Cookie(JWT_COOKIE_NAME, compactJws);
		int durationOfJWTExpiration = (int)((expirationDate.getTime() - System.currentTimeMillis()) / 1000);

		jwtCookie.setMaxAge(durationOfJWTExpiration);
		return jwtCookie;
	}

	/**
	 * Delete the JWT cookie.
	 *
	 * @param request The HTTP request
	 * @param response The HTTP response
	 */
	public static void deleteJwtCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return;
		}

		for (Cookie cookie: cookies) {
			if (cookie.getName().equals(JWT_COOKIE_NAME)) {
				cookie.setValue("");
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}

	/**
	 * Returns a new token containing the token subject from the JWT cookie
	 *
	 * @param request The HTTP request
	 * @return an 'Authentication' implementation token for simple representation of username and password
	 */
	public static Authentication getJWTAuthentication(HttpServletRequest request) {
		JWTPayload tokenPayload = getJwtCookiePayload(request.getCookies());
		if (tokenPayload != null) {
			return new UsernamePasswordAuthenticationToken(
				tokenPayload.getUsername(),
				null,
				Collections.emptyList()
			);
		}

		return null;
	}

	/**
	 * Gets the JSON Web token subject from the cookie
	 *
	 * @param cookies An array of cookies
	 * @returns a boolean indicating whether or not there is a cookie that matches the correct
	 *  JSON Web Token credentials
	 */
	public static JWTPayload getJwtCookiePayload(Cookie[] cookies) {
		if (cookies == null) return null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(JWT_COOKIE_NAME)) {
				Claims jwtPayloadClaims = Jwts.parser()
					.setSigningKey(SIGNING_KEY)
					.parseClaimsJws(cookie.getValue())
					.getBody();

				System.out.println(jwtPayloadClaims.get("roleLevel"));
				System.out.println((Integer) jwtPayloadClaims.get("roleLevel"));
				return new JWTPayload(
					jwtPayloadClaims.getSubject(),
					(Integer) jwtPayloadClaims.get("roleLevel")
				);
			}
		}
		return null;
	}
}
