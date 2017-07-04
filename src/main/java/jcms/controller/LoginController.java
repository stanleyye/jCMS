package jcms.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Key;
import jcms.model.User;
import jcms.service.UserService;
import jcms.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {
    // We need a signing key, so we'll create one just for this example. Usually
    // the key would be read from your application configuration instead.
    // TODO: move this key elsewhere
    private Key key = MacProvider.generateKey();

    private String jwtCookieName = "jCMSCookie";
    private final static String LOGIN_PATH = "/login";
    private final static String REGISTER_PATH = "/register";
    @Autowired
    private UserService userService;

    /*
     * The POST Login controller for the /login endpoint.
     */
    @RequestMapping(value = LOGIN_PATH, method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> login(
            @RequestParam(value="email") String email,
            @RequestParam(value="password") String password,
            HttpServletRequest request,
            HttpServletResponse response) {
        // TODO: Sanitize the parameters for possible security issues
        HttpHeaders responseHeaders = new HttpHeaders();

        /*
         * Check if there are any matching Jwt cookies. If there are, redirect the request to the
         * base URL.
         */
        if (isMatchingJwtSubject(request.getCookies(), jwtCookieName, email)) {
            try {
                setLocationToBaseUrlInHttpHeader(responseHeaders, request);
                return new ResponseEntity<>(null, responseHeaders, HttpStatus.FOUND);
            } catch (URISyntaxException e) {
                // TODO: create a logger to handle these errors / exceptions
                System.out.println(e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        ResponseEntity incorrectUserOrPassResponseEntity =
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Either the username or password is incorrect.");

        // Find the user
        User user = userService.findByEmail(email);

        // Return bad HTTP response if user does not exist
        if (user == null) {
            return incorrectUserOrPassResponseEntity;
        }

        // Return bad HTTP response if password does not match
        boolean isMatchingPassword = UserServiceImplementation.PASSWORD_ENCODER.matches(password, user.getPassword());
        if (!isMatchingPassword) {
            return incorrectUserOrPassResponseEntity;
        }

        try {
            setLocationToBaseUrlInHttpHeader(responseHeaders, request);
        } catch (URISyntaxException e) {
            // TODO: create a logger to handle these errors / exceptions
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.addCookie(createJwtCookie(user.getEmail()));

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.FOUND);
    }

    /*
     * The POST Register controller for the /register endpoint.
     */
    @RequestMapping(value = REGISTER_PATH, method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> register(
            @RequestParam(value="name") String name,
            @RequestParam(value="username") String username,
            @RequestParam(value="email") String email,
            @RequestParam(value="password") String password) {
        // TODO: sanitize parameters
        boolean isExistingUsername = userService.existsByUsername(username);
        boolean isExistingEmail = userService.existsByEmail(email);

        if (isExistingUsername) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The username has already been taken.");
        }

        if (isExistingEmail) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The specified email already exists.");
        }

        // Create and save the user
        // TODO: Set up roles / admin levels
        User newUser = new User(name, username, email, password);
        userService.saveUser(newUser);

        return ResponseEntity.ok("");
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
    private Cookie createJwtCookie(String jwtSubject) {
        // TODO: Set secure / domain / http only parameters for the cookie
        // Sign the JWT using SHA-512 and compact it to a String form
        String compactJws = Jwts.builder()
                .setSubject(jwtSubject)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        final Cookie jwtCookie = new Cookie(jwtCookieName, compactJws);
        // Max age of cookie = 7 days (604800 seconds)
        jwtCookie.setMaxAge(604800);
        return jwtCookie;
    }

    /*
     * Returns the base url of the request url string
     *
     * @param request The HTTP request
     * @return the base URL string
     */
    private String getBaseUrl(HttpServletRequest request) {
        String baseUrl = request
                .getRequestURL()
                .substring(0, request.getRequestURL().length() - request.getRequestURI().length())
                + request.getContextPath();
        return baseUrl;
    }

    /*
     * Indicates whether or not there is a cookie that matches the correct
     * JSON Web Token credentials
     *
     * @param cookies An array of cookies
     * @param jwtCookieName The name of the jCMS cookie that stores the encoded JSON Web token credentials
     * @param subjectToMatch The JSON Web token subject to match
     * @returns a boolean indicating whether or not there is a cookie that matches the correct
     *  JSON Web Token credentials
     */
    private boolean isMatchingJwtSubject(Cookie[] cookies, String jwtCookieName, String subjectToMatch) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(jwtCookieName)) {
                String jwtSubject = Jwts.parser()
                        .setSigningKey(key)
                        .parseClaimsJws(cookie.getValue())
                        .getBody()
                        .getSubject();
                if (jwtSubject.equals(subjectToMatch)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Set the HTTP header location field to the base url
     *
     * @param httpHeader The HTTP header to edit
     * @param request The HTTP Request to extract base url from
     * @throws URISyntaxException
     */
    private void setLocationToBaseUrlInHttpHeader(HttpHeaders httpHeader, HttpServletRequest request) throws URISyntaxException {
        URI location = new URI(getBaseUrl(request));
        httpHeader.setLocation(location);
    }
}
